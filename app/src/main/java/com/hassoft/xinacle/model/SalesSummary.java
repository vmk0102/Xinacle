package com.hassoft.xinacle.model;

public class SalesSummary {
    private  String SalesNo;
    private  String EDate;
    private  String CustomerName;
    private  double GrossAmount;
    private double DiscountAmount;
    private double SalesTaxAmount;
    private double NetAmount;
    private double PaidAmount;
    private double BalanceAmount;
    private double ReturnAmount;


    public String getSalesNo() {
        return SalesNo;
    }

    public void setSalesNo(String salesNo) {
        SalesNo = salesNo;
    }

    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public double getGrossAmount() {
        return GrossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        GrossAmount = grossAmount;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    public double getSalesTaxAmount() {
        return SalesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        SalesTaxAmount = salesTaxAmount;
    }

    public double getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(double netAmount) {
        NetAmount = netAmount;
    }

    public double getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        PaidAmount = paidAmount;
    }

    public double getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public double getReturnAmount() {
        return ReturnAmount;
    }

    public void setReturnAmount(double returnAmount) {
        ReturnAmount = returnAmount;
    }

}
