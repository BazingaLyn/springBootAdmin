package com.geekcattle.aijia.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lgl
 * @time 2017/11/16 15:48
 * @desc
 */
public class TransactionBill {


    //1:BOSS 2:APP 3:POS
    private Integer paySource;

    private String paySourceStr;

    //交易类型，取值：1-收款，2-退款
    private Integer transactionType;

    private String transactionTypeStr;

    //款项类型，取值：14-诚意金，15-定金，16-合同额
    private Integer paymentType;

    private String paymentTypeStr;

    // 收款方式，取值：24-现金；91-微信；90-支付宝；25-POS机；51-网划；92-建业通宝;146-贷款入账
    private Integer payType;

    private String payTypeStr;


    private BigDecimal transactionAmount;

    //收据编号
    private String payNo;

    // 状态：取值：0-待确认收款；1-已确认收款; 2-已驳回收款; 10-待审批退款；11-待退款；12-已退款；13-已驳回退款
    private Integer amountStatus;


    private String amountStatusStr;

    // 是否操作了艾佳币，取值：Y-是，N-否
    private String hasGrantAjb;


    private Date paymentTime;

    public Integer getPaySource() {
        return paySource;
    }

    public void setPaySource(Integer paySource) {
        this.paySource = paySource;
        if(null != paySource){
            switch (paySource){
                case 1 :
                    this.paySourceStr = "BOSS";
                    break;
                case 2 :
                    this.paySourceStr = "APP";
                    break;
                case 3 :
                    this.paySourceStr = "POS";
                    break;
            }
        }
    }

    public String getPaySourceStr() {
        return paySourceStr;
    }

    public void setPaySourceStr(String paySourceStr) {
        this.paySourceStr = paySourceStr;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
        //交易类型，取值：1-收款，2-退款
        if(null != transactionType){
            switch (transactionType){
                case  1:
                    this.transactionTypeStr = "收款";
                    break;
                case  2:
                    this.transactionTypeStr = "退款";
                    break;
            }
        }
    }

    public String getTransactionTypeStr() {
        return transactionTypeStr;
    }

    public void setTransactionTypeStr(String transactionTypeStr) {
        this.transactionTypeStr = transactionTypeStr;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
        if(null != paymentType){
            switch (paymentType){
                //款项类型，取值：14-诚意金，15-定金，16-合同额
                case  14 :
                    this.paymentTypeStr = "诚意金";
                    break;
                case 15 :
                    this.paymentTypeStr = "定金";
                    break;
                case 16 :
                    this.paymentTypeStr = "合同额";
                    break;
            }
        }
    }

    public String getPaymentTypeStr() {
        return paymentTypeStr;
    }

    public void setPaymentTypeStr(String paymentTypeStr) {
        this.paymentTypeStr = paymentTypeStr;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
        //收款方式，取值：24-现金；91-微信；90-支付宝；25-POS机；51-网划；92-建业通宝;146-贷款入账
        if(null != payType){
            switch (payType){
                case  24 :
                    this.payTypeStr = "现金";
                    break;
                case  91 :
                    this.payTypeStr = "微信";
                    break;
                case  90 :
                    this.payTypeStr = "支付宝";
                    break;
                case  25 :
                    this.payTypeStr = "POS机";
                    break;
                case  51 :
                    this.payTypeStr = "网划";
                    break;
                case  92 :
                    this.payTypeStr = "建业通宝";
                    break;
                case  146 :
                    this.payTypeStr = "贷款入账";
                    break;
            }
        }
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Integer getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
        if(null != amountStatus){
            // 状态：取值：0-待确认收款；1-待确认收款; 2-已驳回收款; 10-待审批退款；11-待退款；12-已退款；13-已驳回退款
            switch (amountStatus){
                case  0 :
                    this.amountStatusStr = "待确认收款";
                    break;
                case  1 :
                    this.amountStatusStr = "待确认收款";
                    break;
                case  2 :
                    this.amountStatusStr = "已驳回收款";
                    break;
                case  10 :
                    this.amountStatusStr = "待审批退款";
                    break;
                case  11 :
                    this.amountStatusStr = "待退款";
                    break;
                case  12 :
                    this.amountStatusStr = "已退款";
                    break;
                case  13 :
                    this.amountStatusStr = "已驳回退款";
                    break;
            }
        }
    }

    public String getAmountStatusStr() {
        return amountStatusStr;
    }

    public void setAmountStatusStr(String amountStatusStr) {
        this.amountStatusStr = amountStatusStr;
    }

    public String getHasGrantAjb() {
        return hasGrantAjb;
    }

    public void setHasGrantAjb(String hasGrantAjb) {
        this.hasGrantAjb = hasGrantAjb;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }
}
