package com.geekcattle.task.ajf;

import com.alibaba.fastjson.JSON;
import com.geekcattle.aijia.domain.*;
import com.geekcattle.task.AccountAjfTask;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author lgl
 * @time 2017/11/20 17:12
 * @desc
 */
public class AjbComputeUnit implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(AjbComputeUnit.class);

    private UserInfo userInfo;

    private AccountAjfTask accountAjfTask;

    public AjbComputeUnit(UserInfo userInfo, AccountAjfTask accountAjfTask) {
        this.userInfo = userInfo;
        this.accountAjfTask = accountAjfTask;
    }

    @Override
    public void run() {
        logger.info("threadId is {} compute userInfo {}",Thread.currentThread().getId(), JSON.toJSONString(userInfo));

        try{
            AccountAjfInfo accountAjfInfo = new AccountAjfInfo();

            Integer userId = userInfo.getUserId();
            String mobile = userInfo.getMobile();
            String name = userInfo.getName();

            if(Strings.isNullOrEmpty(name)) name = "未知";

            accountAjfInfo.setUserName(name);
            accountAjfInfo.setUserId(userId);
            accountAjfInfo.setMobile(mobile);

            // 当前用户剩余的AJB余额
            BigDecimal balanceAccount = accountAjfTask.getAccountInfoMapper().queryBalanceAccountByUserId(userId);
            if(null == balanceAccount) balanceAccount = BigDecimal.ZERO;

            accountAjfInfo.setTotalBalanceAmount(balanceAccount);

            BigDecimal realCashAmount = accountAjfTask.getAccountInfoMapper().queryRealReceivablesAmount(userId);
            accountAjfInfo.setTotalChargeRmbAmount(realCashAmount);

            Date ajfEndTime = accountAjfTask.getAccountInfoMapper().queryLastEndTime(userId);
            accountAjfInfo.setEndDate(ajfEndTime);

            BigDecimal recoveryAjfAmount = accountAjfTask.getAccountInfoMapper().queryRecoveryAjfAmount(userId);
            accountAjfInfo.setTotalRecoveryAjfAmount(recoveryAjfAmount);

            //aladdin 订单的信息
            List<AladdinOrder> aladdinOrders = accountAjfTask.getAccountInfoMapper().queryAladdinOrderByUserId(userId);

            if(!CollectionUtils.isEmpty(aladdinOrders)){
                List<AccountAjfInfo.AladdinOrderInfo> aladdinOrderInfos = new ArrayList<>();

                for(AladdinOrder aladdinOrder : aladdinOrders){

                    AccountAjfInfo.AladdinOrderInfo aladdinOrderInfo = new AccountAjfInfo.AladdinOrderInfo();
                    aladdinOrderInfo.setBelongCompanyId(aladdinOrder.getCompanyId());
                    aladdinOrderInfo.setBuildingId(aladdinOrder.getBuildingId());
                    aladdinOrderInfo.setOrderId(aladdinOrder.getOrderNo());

                    if(accountAjfTask.getBuildingInfosMap() != null){
                        aladdinOrderInfo.setBuildingName(accountAjfTask.getBuildingInfosMap().get(aladdinOrderInfo.getBuildingId()));
                    }

                    if(accountAjfTask.getCompanyInfosMaps() != null){
                        aladdinOrderInfo.setBelongCompanyName(accountAjfTask.getCompanyInfosMaps().get(aladdinOrderInfo.getBelongCompanyId()));
                    }
                    List<TransactionBill> transactionBills = accountAjfTask.getAccountInfoMapper().queryAladdinOrderBill(aladdinOrder.getOrderNo());

                    if(!CollectionUtils.isEmpty(transactionBills)){
                        aladdinOrderInfo.setTransactionBills(transactionBills);
                    }
                    aladdinOrderInfos.add(aladdinOrderInfo);
                }
                accountAjfInfo.setAladdinOrderInfoList(aladdinOrderInfos);
            }

            // 艺术品订单信息
            List<OmsOrderInfo> omsOrderInfos = accountAjfTask.getAccountInfoMapper().queryOmsOrderByUserId(userId);
            if(!CollectionUtils.isEmpty(omsOrderInfos)){
                accountAjfInfo.setOmsOrderInfos(omsOrderInfos);
            }

            // 1219活动信息
            Activity1219Info activity1219Info = accountAjfTask.getAccountInfoMapper().query1219ActivityInfo(userId);
            if(null != activity1219Info){
                accountAjfInfo.setActivity1219Info(activity1219Info);
            }

            // 消费艺术品AJB个数
            BigDecimal consumerAjbAmount = accountAjfTask.getAccountInfoMapper().consumerAjbAmount(userId);
            if(null == consumerAjbAmount){
                consumerAjbAmount = BigDecimal.ZERO;
            }

            accountAjfInfo.setConsumerAjbAmount(consumerAjbAmount);

            accountAjfInfo.setIndexTime(new Date());


            accountAjfInfo.setBalanceState();


            logger.info("accountAjbInfo is {}", JSON.toJSONString(accountAjfInfo));

            accountAjfTask.getElasticSearchImpl().indexWithId(accountAjfInfo,accountAjfInfo.getUserId().toString());
        }catch (Exception e){
            logger.error("compute unit occor exception is ",e);
        }
    }
}
