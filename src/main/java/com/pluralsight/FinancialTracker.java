package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTracker {

    /* ------------------------------------------------------------------
       Shared data and formatters
       ------------------------------------------------------------------ */
    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /* ------------------------------------------------------------------
       Main menu
       ------------------------------------------------------------------ */
    public static void main(String[] args) {
        loadTransactions(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===============================");
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("===============================");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D" -> addDeposit(scanner);
                case "P" -> addPayment(scanner);
                case "L" -> ledgerMenu(scanner);
                case "X" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        scanner.close();
    }

    /* ------------------------------------------------------------------
       File I/O
       ------------------------------------------------------------------ */

    /**
     *
     * @param fileName
     */
    public static void loadTransactions(String fileName) {

        // creates file if it does not exist
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.printf("File not found. Created new file %s", fileName);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Issue while creating a new file.");
            }
        }
        // reads each line,
        // parses the five fields(Transaction attributes),
        // builds a Transaction object and adds it to the transaction list
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                LocalDate transactionDate = LocalDate.parse(parts[0],DATE_FMT);
                LocalTime transactionTime = LocalTime.parse(parts[1],TIME_FMT);
                String paymentDescription = parts[2];
                String vendor = parts[3];
                double price = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(transactionDate, transactionTime, paymentDescription, vendor, price));
            } br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ------------------------------------------------------------------
       Add new transactions
       ------------------------------------------------------------------ */

    private static void addDeposit(Scanner scanner) {
        // asks for each attribute one by one, but the date and time
        // all inputs being parsed from String, but the amount
        try{
            //Prompt for ONE date+time string in the format "yyyy-MM-dd HH:mm:ss", plus description, vendor, amount.
            System.out.printf("Enter the date and time of the transaction (follow this format %s): ",DATETIME_PATTERN);
            String transactionDateTime = scanner.nextLine();
            LocalDateTime transactionDateTimeParsed = LocalDateTime.parse(transactionDateTime,DATETIME_FMT);
            LocalDate transactionDateParsed = transactionDateTimeParsed.toLocalDate();
            LocalTime transactionTimeParsed = transactionDateTimeParsed.toLocalTime();
            System.out.print(("Enter the description of the transaction: "));
            String transactionDescription = scanner.nextLine();
            System.out.print("Enter the name of the vendor: ");
            String vendor = scanner.nextLine();
            System.out.println("Enter the amount of the transaction: ");

            double amount = scanner.nextDouble();

            BigDecimal updatedPrice = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
            amount = updatedPrice.doubleValue();
            scanner.nextLine();
            //Validates that the amount entered is positive
            //Stores the amount as-is (positive) and appends to the file
            if (amount > 0 ) {
                transactions.add(new Transaction(transactionDateParsed,transactionTimeParsed,transactionDescription,vendor,amount));
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
                    bw.newLine();
                    bw.write( transactionDateParsed + "|" + transactionTimeParsed + "|" + transactionDescription +"|" +  vendor +"|" + amount);
                    bw.close();
                }
                catch ( Exception e){
                    System.out.println("Something went wrong");
                }
                System.out.println("You added: " + transactions.get(transactions.size()-1));
            }
            else {
                System.out.println("Transaction amount should be a positive number.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Same prompts as addDeposit.
     * Amount must be entered as a positive number,
     * then converted to a negative amount before storing.
     */
    private static void addPayment(Scanner scanner) {
        // TODO
        try {
            System.out.printf("Enter the date and time of the transaction (follow this format %s): ",DATETIME_PATTERN);
            String transactionDateTime = scanner.nextLine();
            LocalDateTime transactionDateTimeParsed = LocalDateTime.parse(transactionDateTime,DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            LocalDate transactionDateParsed = LocalDate.parse(transactionDateTimeParsed.toLocalDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            LocalTime transactionTimeParsed = LocalTime.parse(transactionDateTimeParsed.toLocalTime().format(DateTimeFormatter.ofPattern(TIME_PATTERN)));
            System.out.print(("Enter the description of the transaction: "));
            String transactionDescription = scanner.nextLine();
            System.out.print("Enter the name of the vendor: ");
            String vendor = scanner.nextLine();
            System.out.println("Enter the amount of the transaction: ");
            double price = scanner.nextFloat();
            scanner.nextLine();
            if (price <0){
                transactions.add(new Transaction(transactionDateParsed,transactionTimeParsed,transactionDescription,vendor,price));
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
                    bw.newLine();
                    bw.write( transactionDateParsed + "|" + transactionTimeParsed + "|" + transactionDescription +"|" +  vendor +"|" + "-" + price);
                    bw.close();
                }
                catch ( Exception e){
                    System.out.println("Something went wrong");
                }
                System.out.println("You added: " + transactions.get(transactions.size()-1));
            }
            else {
                System.out.println("Transaction amount should be a positive number. It will be converted to a negative number automatically.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ------------------------------------------------------------------
       Ledger menu
       ------------------------------------------------------------------ */
    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("===============================");
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("===============================");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A" -> displayLedger(transactions);
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> reportsMenu(scanner);
                case "H" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Display helpers: show data in neat columns
       ------------------------------------------------------------------ */
    private static void displayLedger(ArrayList<Transaction> transactions) { /* TODO – print all transactions in column format */
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void displayDeposits() { /* TODO – only amount > 0               */
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    private static void displayPayments() { /* TODO – only amount < 0               */
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }

    /* ------------------------------------------------------------------
       Reports menu
       ------------------------------------------------------------------ */
    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("===============================");
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");
            System.out.println("===============================");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> monthToDateReport(); /* TODO – month-to-date report */
                case "2" -> previousMonthReport(); /* TODO – previous month report */
                case "3" -> yearToDateReport(); /* TODO – year-to-date report   */
                case "4" -> previousYearReport(); /* TODO – previous year report  */
                case "5" -> searchByVendor(scanner); /* TODO – prompt for vendor then report */
                case "6" -> customSearch(scanner);
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Reporting helpers
       ------------------------------------------------------------------ */
    //private static void filterTransactionsByDate(LocalDate start, LocalDate end) {
    // TODO – iterate transactions, print those within the range}

    //private static void filterTransactionsByVendor(String vendor) {
    // TODO – iterate transactions, print those with matching vendor}

    private static void searchByVendor(Scanner scanner) {
        System.out.print("Enter the vendor name: ");
        String vendor = scanner.nextLine();
        for (Transaction transaction : transactions) {
            if (vendor.equalsIgnoreCase(transaction.getVendor())){
                System.out.println(transaction);
            }
        }
    }

    private static void previousYearReport() {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionDate().getYear() == (LocalDate.now().getYear() - 1)){
                System.out.println(transaction);
            }
        }
    }

    private static void yearToDateReport() {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transaction);
            }
        }
    }

    private static void previousMonthReport() {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionDate().getMonthValue() == (LocalDate.now().minusMonths(1).getMonthValue()) &&
                    transaction.getTransactionDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transaction);
            }
        }
    }
    //SOMETHING WRONG
    private static void monthToDateReport() {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionDate().getMonth() == LocalDate.now().getMonth() &&
                    transaction.getTransactionDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transaction);
            }
        }
    }


    private static void customSearch(Scanner scanner) {
        // TODO – prompt for any combination of date range, description,
        //        vendor, and exact amount, then display matches

        //filteredList is used to store transactions after we trim off excluded one
        //excludedList is used to store transactions to remove from filteredList
        ArrayList<Transaction> filteredList = new ArrayList<>(transactions);
        ArrayList<Transaction> excludedList = new ArrayList<>();
        System.out.print("Enter start date of the transaction (year-month-day, 2025-09-24): ");
        try{
            String date = scanner.nextLine();
            LocalDate parsedDate = LocalDate.parse(date);
            for (Transaction transaction : filteredList) {
                if (parsedDate.isAfter(transaction.getTransactionDate())) {
                    excludedList.add(transaction);
                }
            }
        } catch (Exception e) {}
        filteredList.removeAll(excludedList);


        System.out.print("Enter end date of the transaction (year-month-day, 2025-09-24): ");
        try {
            String date = scanner.nextLine();
            LocalDate parsedDate = LocalDate.parse(date);
            for (Transaction transaction : filteredList) {
                if (parsedDate.isBefore(transaction.getTransactionDate())) {
                    excludedList.add(transaction);
                }
            }
        } catch (Exception e) {}
        filteredList.removeAll(excludedList);


        System.out.print("Enter the transaction description: ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            for (Transaction transaction : filteredList) {
                if (!description.equalsIgnoreCase(transaction.getTransactionDescription())){
                    excludedList.add(transaction);
                    System.out.println(transaction);
                }
            }
        }
        filteredList.removeAll(excludedList);


        System.out.println("Enter the vendor name: ");
        String vendor  = scanner.nextLine();
        if (!vendor.isEmpty()){
            for (Transaction transaction : filteredList) {
                if (!vendor.equalsIgnoreCase(transaction.getVendor())){
                    excludedList.add(transaction);
                }
            }
        }
        filteredList.removeAll(excludedList);


        System.out.println("Enter minimum amount:");
        try {
            int priceMin = Integer.parseInt(scanner.nextLine());
            for (Transaction transaction : filteredList) {
                if(priceMin > transaction.getAmount()){
                    excludedList.add(transaction);
                }
            }
        } catch (Exception e) {}
        filteredList.removeAll(excludedList);


        System.out.println("Enter maximum amount:");
        try {
            int priceMax = Integer.parseInt(scanner.nextLine());
            for (Transaction transaction : filteredList) {
                if(priceMax < transaction.getAmount()){
                    excludedList.add(transaction);
                }
            }
        } catch (Exception e) {}
        filteredList.removeAll(excludedList);


        System.out.println("===============================");
        System.out.println("Custom search results:");
        for (Transaction customSearchTransaction : filteredList) {
            System.out.println(customSearchTransaction);
        }
        System.out.println("===============================");
    }

    /* ------------------------------------------------------------------
       Utility parsers (you can reuse in many places)
       ------------------------------------------------------------------ */

    //private static LocalDate parseDate(String s) {
        /* TODO – return LocalDate or null */
        //return LocalDate.parse(s);}

    //private static Double parseDouble(String s) {
        /* TODO – return Double   or null */
        //return (double) Integer.parseInt(s);
    //}

}
