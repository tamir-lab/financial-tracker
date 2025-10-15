package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private String transactionDescription;
    private String vendor;
    private double price;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalTime transactionTime) {
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

    public Transaction(LocalDate transactionDate, LocalTime transactionTime, String transactionDescription, String vendor, double price) {
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.transactionDescription = transactionDescription;
        this.vendor = vendor;
        this.price = price;


    }
}