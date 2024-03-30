package org.example.console;

import org.example.exceptions.BankAccountOperationException;
import org.example.model.BankAccount;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.Main.scanner;

/**
 * The interactive console class;
 * The interoperable menus are generated using the methods below;
 * Using static helper methods to validate all input given;
 */

public class ATMMachine {

    //our helper objects;
    //LOGGER - displaying information throughout code execution;
    private static final Logger LOGGER = LoggerFactory.getLogger(ATMMachine.class);

    //existingTestUser - created for the purpose of main menu option 3 (ATM trial use);
    private static BankAccount existingTestUser = new BankAccount("Test User",
            "12345678", 10000);

    //newAccount - populated with values passed while creating a new account;
    //newAccountCreated - boolean that is set to true once new account gets created;
    private static BankAccount newAccount;
    private static boolean newAccountCreated = false;

    //flag used for while loops (proper functioning of menus);
    private static boolean flag = true;

    //random - used for generating a random 8-digit account number;
    private static Random random = new Random();

    //main menu of the ATM machine;
    public static void accessATMMachine() throws BankAccountOperationException {


        boolean flag = true;

        System.out.println("""
                                
                Warm greetings and welcome to Test Bank ATM Machine!
                If you are an existing account holder at our bank, please insert your card, to use the ATM machine.
                Otherwise, you can be redirected to one of our local service desks to open a new account with us.
                If you would just like to see how our ATM Machine works, a trial usage is available.
                """);
        while (flag) {

            try {

                displayOpeningMenu();
                int option = validateMenuOption(scanner.nextLine());

                switch (option) {

                    case 1: {

                        if (!newAccountCreated) {

                            LOGGER.info("""
                                    You have not created an account with us;
                                    Either create a new account (option 2), or make a trial use of our ATM (option 3).
                                    """);
                        } else {

                            System.out.printf("Welcome, %s!%n", newAccount.getAccountHolderName());

                            if (validatePinNumber(newAccount)) {
                                displayATMOptions(newAccount);
                            }
                        }

                        LOGGER.info("Returning to ATM Machine main screen.");
                        break;
                    }

                    case 2: {

                        if (newAccountCreated) {
                            LOGGER.info("Welcome, %s! You already have an account with us.".formatted(newAccount.getAccountHolderName()));
                        } else {
                            createNewAccount();
                        }
                        LOGGER.info("Returning to ATM Machine main screen.");
                        break;
                    }

                    case 3: {

                        LOGGER.info("Trial use of ATM initiated. Test account balance set to 10000.");
                        existingTestUser.setBalance(10000);
                        displayATMOptions(existingTestUser);
                        LOGGER.info("Returning to ATM Machine main screen.");
                        break;
                    }

                    case 4: {

                        LOGGER.info("Thank you for using our ATM Machine. Have a splendid day!");
                        flag = false;
                        break;
                    }

                    default: {

                        LOGGER.info("Invalid input, please select one of the 4 options.");
                        break;
                    }
                }
            } catch (BankAccountOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //option 2 of the main menu - creating a new account;
    private static void createNewAccount() throws BankAccountOperationException {

        System.out.println("Welcome to our service desk. We shall proceed with creating your account with us;\n" +
                "Please introduce your full name: ");
        String fullName = validateFullName(scanner.nextLine());

        String accountNumber = java.lang.String.valueOf(random.nextInt(10000000, 99999999));
        System.out.printf("An 8-digit account number has been generated for you: %s%n", accountNumber);

        newAccount = new BankAccount(fullName, accountNumber);

        System.out.println("Please introduce your PIN number: ");
        int newPIN = validatePinNumber(scanner.nextLine());
        newAccount.setPinNumber(newPIN);

        System.out.println("""
                        
                Would you like to make an initial deposit to your account?"
                1. Yes, please.
                2. No, thank you.
                """);

        while (flag) {

            int initialDepositOption = validateMenuOption(scanner.nextLine());
            switch (initialDepositOption) {

                case 1: {

                    flag = false;
                    System.out.println("Please enter the amount: ");
                    double initialDeposit = validateDepositWithdrawal(scanner.nextLine());
                    newAccount.setBalance(initialDeposit);
                    break;
                }

                case 2: {

                    flag = false;
                    break;
                }

                default: {

                    LOGGER.info("Invalid input, please select one of the 2 options.");
                    break;
                }
            }
        }

        flag = true;
        System.out.println("Congratulations! Your account at Test Bank has been created.");
        newAccountCreated = true;
    }

    //option 1 and 3 of the main menu (1 - for new account, 3 - for trial use);
    private static void displayATMOptions(BankAccount account) {

        boolean flag = true;

        while (flag) {

            displayMenuOfATM();
            try {
                int option = validateMenuOption(scanner.nextLine());

                switch (option) {

                    case 1: {

                        account.displayBalance();
                        break;
                    }

                    case 2: {

                        System.out.println("Please insert the amount to deposit: ");
                        double inputDeposit = validateDepositWithdrawal(scanner.nextLine());
                        account.addFunds(inputDeposit);
                        break;
                    }

                    case 3: {

                        System.out.println("Please insert the amount to withdraw: ");
                        double inputWithdrawal = validateDepositWithdrawal(scanner.nextLine());
                        account.withdrawFunds(inputWithdrawal);
                        break;
                    }

                    case 4: {

                        flag = false;
                        break;
                    }

                    default: {

                        LOGGER.info("Invalid input, please select one of the 4 options.");
                        break;
                    }
                }
            } catch (NumberFormatException | BankAccountOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //option 1 of the main menu (to access ATM options, we need to enter correct PIN);
    private static boolean validatePinNumber(BankAccount account) {

        while (flag) {

            System.out.println("Please insert the PIN number: ");
            int pinInput = validatePinNumber(scanner.nextLine());
            String attemptOutput = account.insertPinNumber(pinInput);

            if (attemptOutput.contains("correct")) {
                System.out.println("PIN number is correct.");
                return true;
            } else if (attemptOutput.contains("restart")) {
                System.out.println("Maximum wrong attempts reached, returning to main screen.");
                return false;
            } else {
                System.out.println(STR."Wrong PIN number (\{3 - account.getAttempts()} attempt(s) left). Please insert again: ");
            }
        }

        return false;
    }

    //helper methods for displaying information and validating input;
    private static void displayOpeningMenu() {
        System.out.println("""
                Your options are the following:
                                
                1. Insert credit card (use ATM);
                2. Proceed to service desk (create account);
                3. Trial use of ATM - sample test account details are provided, no PIN required;
                4. Exit.
                """);
    }

    private static void displayMenuOfATM() {
        System.out.println("""
                                
                What ATM operation would you like to perform?
                1. Display account balance;
                2. Deposit funds;
                3. Withdraw funds;
                4. Exit to main menu.
                """);
    }

    private static String validateFullName(String input) {

        while (true) {

            if (input.matches("[a-zA-Z]+")) return input;
            else {

                System.out.println("Wrong input! Please insert letters only for name: ");
                input = scanner.nextLine();
            }
        }
    }

    private static int validatePinNumber(String input) {

        while (true) {

            try {

                int parsedInput = Integer.parseInt(input);
                if(parsedInput < 1000 || parsedInput > 9999) throw new NumberFormatException();
                return parsedInput;
            } catch (NumberFormatException e) {

                System.out.println("Wrong input! Please insert 4 digits for PIN: ");
            }
            input = scanner.nextLine();
        }
    }

    private static int validateMenuOption(String input) {

        while (true) {

            try {

                int parsedInput = Integer.parseInt(input);
                return parsedInput;
            } catch (NumberFormatException e) {

                System.out.println("Wrong input! Please insert a number for menu option: ");
            }

            input = scanner.nextLine();
        }
    }

    private static double validateDepositWithdrawal(String input) {

        while (true) {

            try {
                if(input.contains("d") || input.contains("f") || input.contains("e")) throw new NumberFormatException();

                double parsedInput = Double.parseDouble(input);
                if(parsedInput < 0) throw new NumberFormatException();

                return parsedInput;
            } catch (NumberFormatException e) {

                System.out.println("Wrong input! Please insert a positive, real number for deposits or withdrawals: ");
            }

            input = scanner.nextLine();

        }

    }
}
