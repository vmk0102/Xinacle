package com.hassoft.xinacle.model;

public class RunningStock {

    private int ProductID;
    private String ProductName;
    private Double RunningStock;
    private int SerialNo;
    private int ProductNumber;

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Double getRunningStock() {
        return RunningStock;
    }

    public void setRunningStock(Double runningStock) {
        RunningStock = runningStock;
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
        SerialNo = serialNo;
    }

    public int getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(int productNumber) {
        ProductNumber = productNumber;
    }

}
