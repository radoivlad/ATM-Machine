# ATM-Machine (INSTALLATION GUIDE BELOW)

Hello and welcome to ATM-Machine! :)

An interactive console resembling an ATM machine, allowing for account creation and accessing different options with regards to an account.

I have built this project having the final course of First Steps in Programming Java (IT Factory course) as my starting point and inspiration.

The interactive menu offers 3 possibilities:
1. User can create their own account;
2. User can access the ATM machine using their configured PIN number (within a maximum of 3 allowed failed attempts);
3. User can simply have a trial run on the ATM machine, with a preset test account (default balance: 10.000);

The ATM Machine can be operated for displaying account balance, depositing, withdrawing funds or returning to the main menu. Informative messages will be displayed throughout program execution, including by Logger object.

Any errors that may occur with wrong input given to the console, either format (ex: improper menu options, double values for depositing/withdrawing, name etc.) or invalid operation (ex: withdrawal exceeding current balance), will be handled accordingly. A corresponding message will be printed to the console, as to reintroduce valid input.

The user can switch between multiple menu variants, in accordance to desired output: main menu, create new account, operate ATM options, validate account PIN for accessing ATM.

**MAVEN Dependencies used:**
slf4j-api
logback-classic

**-> For proper functioning of the Logger object;**


# HOW TO INSTALL AND USE THE PROJECT

# a. Open with Intellij, using repository link:

1. Press the green button labelled "Code" (top right corner of the project files) to copy the repository link;
2. Alternatively, you can copy it directly from here: https://github.com/radoivlad/ATM-Machine.git 
3. Open IntelliJ (make sure you have **Intellij Community Edition** installed);
4. Top-left corner: **File -> New -> Project from Version Control**;
5. Select Git as **Version control**;
6. Paste repository link in **URL** field;
7. Open the **Main** class and run the ATM-Machine (Shift + F10);

# b. Clone repository to local drive, open with IntelliJ (locally):

1. Navigate to your local drive repository folder (where you would like to have the project cloned);
2. **Right-click -> Open Git Bash here** (make sure you have **Git** installed);
3. Type: **git clone https://github.com/radoivlad/ATM-Machine.git** (this will create a clone of the project in the current directory);
4. Open IntelliJ;
5. Top-left corner: **File -> Open**;
6. Select the folder we cloned earlier;
7. Open the **Main** class and run the ATM-Machine (Shift + F10);

# Finally, have fun! :D //really open to any suggestions, hit me up
