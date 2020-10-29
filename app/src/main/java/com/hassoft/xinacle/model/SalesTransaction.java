package com.hassoft.xinacle.model;

public class SalesTransaction {
    private String SalesNo;
    private String EDate;
    private Float QuantityBox;
    private float Rate;
    private float Discount;
    private float SalesTax;
    private float GrossAmount;

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

    public Float getQuantityBox() {
        return QuantityBox;
    }

    public void setQuantityBox(Float quantityBox) {
        QuantityBox = quantityBox;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    public float getDiscount() {
        return Discount;
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }

    public float getSalesTax() {
        return SalesTax;
    }

    public void setSalesTax(float salesTax) {
        SalesTax = salesTax;
    }

    public float getGrossAmount() {
        return GrossAmount;
    }

    public void setGrossAmount(float grossAmount) {
        GrossAmount = grossAmount;
    }

    public float getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(float netAmount) {
        NetAmount = netAmount;
    }

    public float getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        DiscountAmount = discountAmount;
    }

    public float getSalesTaxAmount() {
        return SalesTaxAmount;
    }

    public void setSalesTaxAmount(float salesTaxAmount) {
        SalesTaxAmount = salesTaxAmount;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    private float NetAmount;
    private float DiscountAmount;
    private float SalesTaxAmount;
    private String CustomerName;
    private String ProductName;

}
