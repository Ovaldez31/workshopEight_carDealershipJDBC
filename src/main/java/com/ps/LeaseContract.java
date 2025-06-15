package com.ps;

public class LeaseContract {
    private int id;
    private String vin;
    private String customerName;
    private double monthlyPayment;
    private double leaseTerm;
    private String date;

    public LeaseContract(String vin, String customerName, double monthlyPayment, double leaseTerm, String date) {
        this.id = id;
        this.vin = vin;
        this.customerName = customerName;
        this.monthlyPayment = monthlyPayment;
        this.leaseTerm = leaseTerm;
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

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(double leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LeaseContract{" +
                "ID=" + id +
                ", VIN='" + vin + '\'' +
                ", Customer='" + customerName + '\'' +
                ", Monthly Payment=$" + monthlyPayment +
                ", Lease Term=" + leaseTerm + " months" +
                ", Date='" + date + '\'' +
                '}';
    }
}