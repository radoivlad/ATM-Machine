package org.example.model;

import org.example.exceptions.BankAccountOperationException;

/**
 * The BankAccount model;
 */

public class BankAccount {

    //encapsulated fields;
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private int pinNumber;
    private int attempts;

    //usual constructor, when creating a new customer account;
    public BankAccount(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }

    //second constructor used for creating the test account (option 3);
    public BankAccount(String accountHolderName, String accountNumber, double balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    //needed setters and getters, with appropriate validation;
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setPinNumber(int pinNumber) throws BankAccountOperationException {
        if(pinNumber < 1000 || pinNumber > 9999) throw new BankAccountOperationException("PIN number needs to be a 4-digit number.");
        this.pinNumber = pinNumber;
    }

    public void setBalance(double balance) throws BankAccountOperationException {
        if(balance < 0) throw new BankAccountOperationException("Balance account has to be positive.");
        this.balance = balance;
    }

    //custom methods, called in the console (display funds, add, withdraw, validate pin - 3 tries allowed system);
    public void displayBalance() {

        System.out.println("Your account's actual balance is: " + balance);
    }

    public void addFunds(double deposit) {

        balance += deposit;
        System.out.println("Deposit successful. Actual balance: " + balance);
    }

    public void withdrawFunds(double withdrawal) throws BankAccountOperationException {

        if(withdrawal > balance) {
            throw new BankAccountOperationException("Insufficient funds for withdrawal. Actual balance: " + balance);
        }
        balance -= withdrawal;
        System.out.println("Withdrawal successful. Actual balance: " + balance);
    }

    public String insertPinNumber(int attemptNumber) {

        if(attemptNumber == pinNumber) {
            attempts = 0;
            return "PIN number is correct.";
        }
        attempts++;
        if(attempts == 3) {
            attempts = 0;
            return "Maximum wrong number of inputs reached. Please restart the authentication process!";
        }
        return "Wrong PIN number introduced, please try again.";
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountHolderName='" + accountHolderName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", pinNumber=" + pinNumber +
                ", attempts=" + attempts +
                '}';
    }
}