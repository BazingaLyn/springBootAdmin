package com.geekcattle.aijia.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author lgl
 * @time 2017/11/16 15:02
 * @desc
 */
public class AccountAjfInfo {

    private Integer userId;

    private String mobile;

    private String userName;

    private Date endDate;

    private List<AladdinOrderInfo> aladdinOrderInfoList;

    private List<OmsOrderInfo> omsOrderInfos;

    private BigDecimal totalBalanceAmount;

    private Activity1219Info activity1219Info;

    private BigDecimal consumerAjbAmount;

    private Date indexTime;

    public Date getIndexTime() {
        return indexTime;
    }

    public void setIndexTime(Date indexTime) {
        this.indexTime = indexTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Activity1219Info getActivity1219Info() {
        return activity1219Info;
    }

    public void setActivity1219Info(Activity1219Info activity1219Info) {
        this.activity1219Info = activity1219Info;
    }

    public List<AladdinOrderInfo> getAladdinOrderInfoList() {
        return aladdinOrderInfoList;
    }

    public void setAladdinOrderInfoList(List<AladdinOrderInfo> aladdinOrderInfoList) {
        this.aladdinOrderInfoList = aladdinOrderInfoList;
    }

    public List<OmsOrderInfo> getOmsOrderInfos() {
        return omsOrderInfos;
    }

    public void setOmsOrderInfos(List<OmsOrderInfo> omsOrderInfos) {
        this.omsOrderInfos = omsOrderInfos;
    }

    public BigDecimal getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    public void setTotalBalanceAmount(BigDecimal totalBalanceAmount) {
        this.totalBalanceAmount = totalBalanceAmount;
    }

    public BigDecimal getConsumerAjbAmount() {
        return consumerAjbAmount;
    }

    public void setConsumerAjbAmount(BigDecimal consumerAjbAmount) {
        this.consumerAjbAmount = consumerAjbAmount;
    }


    public static class AladdinOrderInfo {

        private Integer orderId;

        private Integer buildingId;

        private String buildingName;

        private Integer belongCompanyId;

        private String belongCompanyName;

        private BigDecimal totalCashAmount;

        private List<TransactionBill> transactionBills;

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(Integer buildingId) {
            this.buildingId = buildingId;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public Integer getBelongCompanyId() {
            return belongCompanyId;
        }

        public void setBelongCompanyId(Integer belongCompanyId) {
            this.belongCompanyId = belongCompanyId;
        }

        public String getBelongCompanyName() {
            return belongCompanyName;
        }

        public void setBelongCompanyName(String belongCompanyName) {
            this.belongCompanyName = belongCompanyName;
        }

        public BigDecimal getTotalCashAmount() {
            return totalCashAmount;
        }

        public void setTotalCashAmount(BigDecimal totalCashAmount) {
            this.totalCashAmount = totalCashAmount;
        }

        public List<TransactionBill> getTransactionBills() {
            return transactionBills;
        }

        public void setTransactionBills(List<TransactionBill> transactionBills) {
            this.transactionBills = transactionBills;
        }
    }



    public static class PaymentRecordInfo {


        private String transactionType;

        private String moneyType;

        private String paymentSource;

        private String paymentMode;

        private BigDecimal amount;

        private String status;

        private Date paymentTime;

        public Date getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(Date paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public String getMoneyType() {
            return moneyType;
        }

        public void setMoneyType(String moneyType) {
            this.moneyType = moneyType;
        }

        public String getPaymentSource() {
            return paymentSource;
        }

        public void setPaymentSource(String paymentSource) {
            this.paymentSource = paymentSource;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }




}
