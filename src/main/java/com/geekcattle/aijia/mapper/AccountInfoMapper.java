package com.geekcattle.aijia.mapper;

import com.geekcattle.aijia.domain.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author lgl
 * @time 2017/11/16 10:15
 * @desc
 */
@Repository
public interface AccountInfoMapper {

    BaseAccountInfo selectTestOne(Integer id);


    List<UserInfo> selectHasAjbUserInfo();

    /**
     *
     * @param userId
     * @return
     */
    List<AladdinOrder> queryAladdinOrderByUserId(Integer userId);


    /**
     *
     * @return
     */
    List<CompanyInfo> queryAllCompanyInfo();


    /**
     *
     * @return
     */
    List<BuildingInfo> queryAllBuildingInfo();

    /**
     *
     * @param userId
     * @return
     */
    BigDecimal queryRealReceivablesAmount(Integer userId);

    /**
     *
     * @param orderNo
     * @return
     */
    List<TransactionBill> queryAladdinOrderBill(Integer orderNo);

    /**
     *
     * @param userId
     * @return
     */
    List<OmsOrderInfo> queryOmsOrderByUserId(Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    Activity1219Info query1219ActivityInfo(Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    BigDecimal consumerAjbAmount(Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    BigDecimal queryBalanceAccountByUserId(Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    Date queryLastEndTime(Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    BigDecimal queryRecoveryAjfAmount(Integer userId);
}
