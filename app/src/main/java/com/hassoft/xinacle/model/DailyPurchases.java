package com.hassoft.xinacle.model;

public class DailyPurchases {
    private String EDate;
    private Double TotalPurchases;


    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public Double getTotalPurchases() {
        return TotalPurchases;
    }

    public void setTotalSales(Double totalPurchases) {
        TotalPurchases = totalPurchases;
    }
}
