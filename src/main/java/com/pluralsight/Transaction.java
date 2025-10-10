package com.pluralsight;

public class Transaction {
    private String transactionDate;
    private String transactionTime;
    private String transactionDescription;
    private String vendor;
    private double price;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
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
                "paymentDate='" + transactionDate + '\'' +
                ", paymentTime='" + transactionTime + '\'' +
                ", paymentDescription='" + transactionDescription + '\'' +
                ", vendor='" + vendor + '\'' +
                ", price=" + price +
                '}';
    }

    public Transaction(String transactionDate, String transactionTime, String transactionDescription, String vendor, double price) {
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.transactionDescription = transactionDescription;
        this.vendor = vendor;
        this.price = price;


    }
}
