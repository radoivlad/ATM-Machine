package org.example;

import org.example.console.ATMMachine;
import org.example.exceptions.BankAccountOperationException;

import java.util.Scanner;

/**
 * Main class of our ATM, used for calling the static method accessATMMachine();
 */

public class Main {

    public static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws BankAccountOperationException {

        ATMMachine.accessATMMachine();

        System.out.println();
    }
}