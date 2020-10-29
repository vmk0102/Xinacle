package com.hassoft.xinacle.model;

public class PurchaseTransaction {
   private String PurchaseNo;
   private String  EDate;
   private float QuantityBox;
   private float Rate;
   private float Discount;
   private float SalesTax;
   private float GrossAmount;
   private float NetAmount;
   private float DiscountAmount;
   private float SalesTaxAmount;
   private String SupplierName;
   private String ProductName;
   private String InvoiceNo;

    public String getPurchaseNo() {
        return PurchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        PurchaseNo = purchaseNo;
    }

    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public float getQuantityBox() {
        return QuantityBox;
    }

    public void setQuantityBox(float quantityBox) {
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

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }
}
/*
[{\"PurchaseNo\":\"00071\",\"EDate\":\"05/08/2020\",\"QuantityBox\":1000.00,\"Rate\":10.00,\"Discount\":1.00,\"SalesTax\":11.00,\"GrossAmount\":10000.00,\
"NetAmount\":11000.00,\"DiscountAmount\":100.00,\"SalesTaxAmount\":1100.00,\"SupplierName\":\"SAAD\",\"ProductName\":\"FABRIC\",\"InvoiceNo\":\"545664\"},
 */