# Personal Financial Tracker

## Description of the Project

This application helps users to record deposits and payments, view transaction history, and generate basic financial reports. It uses file storage for data persistence and provides an easy-to-use interface for managing personal finances. Users can input transaction details, which are saved locally to ensure data is retained between sessions. The application then processes and displays the data in a structured format, making it easy to review and analyze financial activity over time.

## User Stories


- As a Customer, I want to  be able to see Home Screen, so I can choose what I want to do.
- As a Customer I want to be able to add a deposit, so I can keep track of spending.
- As a Customer, I want to beable to make a payment, so I can save payment information.
- As a Customer, I want to be able to open the ledger, so I can see structured records.
- As a Customer, I want to be able to open all entries, so I can see the entire history of entries.
- As a Customer, I want to be able to open the payments section, so I can see only the payments.
- As a Customer, I want to be able to open the deposits section, so I can see only deposits. 
- As a Customer, I want to be able to exit the app, so I can close the app. 
- As a Customer, I want to be able to return to the home screen, so I can keep using the application.
- As a Customer, I want to be able to see only the transactions for the current month, so I can keep track of recent activity.
- As a Customer, I want to be able to from the previous month, so I can review my past month's activity.
- As a Customer, I want to be able to see transactions from the current year to date, so can review annual activity.
- As a Customer, I want to be able to search transactions by vendor name, so I can review payments or deposits related to a specific vendor. 
- As a Customer I want to see the activity for the previous year, I can keep compare it.
- As a Customer I want to be able to make a custom search, so I can get a report by specific filter.

## Setup

Instructions on how to set up and run the project using IntelliJ IDEA.

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select 'Run 'YourMainClassName.main()'' to start the application.

## Technologies Used

- Java 17

- IntelliJ IDEA

- Git & GitHub for version control

- CSV file I/O for data persistence

- LocalDate / LocalTime API for date and time handling

- BigDecimal and RoundingMode for formatting an amount input

- BufferedReader / BufferedWriter for reading and writing files

## Demo

- Checked "Add Deposit", "Make Payment" with the wrong input, "All" to print out all transactions; and "Custom Search".

[Demo GIF](demo%20gif.gif)

## Future Work

Outline potential future enhancements or functionalities you might consider adding:

- Removing transactions.
- Updating transactions. Change a value of particular attribute.
- Category tagging. Add a category field to each transaction.
- Summary Report. Reflect balance and total. 
- Sort options. Sort by newest to oldest, oldest to newest, highest to lowest amount, lowest to highest amount etc.


## Resources

List resources such as tutorials, articles, or documentation that helped you during the project.

- Pluralsight workbooks
- [Potato Sensei](https://chatgpt.com/g/g-681d378b0c90819197b16e49abe384ec-potato-sensei)

## Team Members

- **Name 1** - Tamir, a code writer.
- **Name 2** - Roger, a cheerleader.

## Thanks

- Chokran to Potato Sensei for continuous support and guidance.
- A special thanks to Jason.
