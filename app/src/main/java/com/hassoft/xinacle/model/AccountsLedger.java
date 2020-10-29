package com.hassoft.xinacle.model;

public class AccountsLedger {
    private String Date;
    private String VoucherID;
    private String TypeName;
    private Double DebitAmount;
    private Double CreditAmount;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(String voucherID) {
        VoucherID = voucherID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public Double getDebitAmount() {
        return DebitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        DebitAmount = debitAmount;
    }

    public Double getCreditAmount() {
        return CreditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        CreditAmount = creditAmount;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getAccountTitle() {
        return AccountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        AccountTitle = accountTitle;
    }

    private Double Balance;
    private String AccountTitle;



}
