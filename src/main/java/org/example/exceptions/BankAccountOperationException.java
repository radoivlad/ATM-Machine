package org.example.exceptions;

/**
 * Custom exception destined for throwing errors when inputting or setting wrong values in the bank account;
 */

public class BankAccountOperationException extends Exception{

    public BankAccountOperationException(String message) {
        super(message);
    }
}
