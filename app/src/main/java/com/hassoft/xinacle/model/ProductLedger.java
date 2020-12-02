package com.hassoft.xinacle.model;

public class ProductLedger {
    private String EDate;
    private Float In;
    private Float Out;
    private Float Balance;
    private String ProductName;
    private String Particular;
    private Float OpenningStock;
    private String WaraehouseName;
    private int TransactionNo;

    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public Float getIn() {
        return In;
    }

    public void setIn(Float in) {
        In = in;
    }

    public Float getOut() {
        return Out;
    }

    public void setOut(Float out) {
        Out = out;
    }

    public Float getBalance() {
        return Balance;
    }

    public void setBalance(Float balance) {
        Balance = balance;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getParticular() {
        return Particular;
    }

    public void setParticular(String particular) {
        Particular = particular;
    }

    public Float getOpenningStock() {
        return OpenningStock;
    }

    public void setOpenningStock(Float openningStock) {
        OpenningStock = openningStock;
    }

    public String getWaraehouseName() {
        return WaraehouseName;
    }

    public void setWaraehouseName(String waraehouseName) {
        WaraehouseName = waraehouseName;
    }

    public int getTransactionNo() {
        return TransactionNo;
    }

    public void setTransactionNo(int transactionNo) {
        TransactionNo = transactionNo;
    }
}
