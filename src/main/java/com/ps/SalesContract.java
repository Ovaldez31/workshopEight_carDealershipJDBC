package com.ps;

public class SalesContract {
    private int id;
    private String vin;
    private String customerName;
    private double salesPrice;
    private String date;

    public SalesContract(String vin, String customerName, double salesPrice, String date) {
        this.id = id;
        this.vin = vin;
        this.customerName = customerName;
        this.salesPrice = salesPrice;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SalesContract{" +
                "ID=" + id +
                ", VIN='" + vin + '\'' +
                ", Customer='" + customerName + '\'' +
                ", Sales Price=$" + salesPrice +
                ", Date='" + date + '\'' +
                '}';
    }
}