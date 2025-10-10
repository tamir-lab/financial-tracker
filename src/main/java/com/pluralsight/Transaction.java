package com.pluralsight;

public class Transaction {
    private String paymentDate;
    private String paymentTime;
    private String paymentDescription;
    private String vendor;
    private double price;

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "paymentDate='" + paymentDate + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", paymentDescription='" + paymentDescription + '\'' +
                ", vendor='" + vendor + '\'' +
                ", price=" + price +
                '}';
    }

    public Transaction(String paymentDate, String paymentTime, String paymentDescription, String vendor, double price) {
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.paymentDescription = paymentDescription;
        this.vendor = vendor;
        this.price = price;


    }
}
