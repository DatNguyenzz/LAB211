/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AccountController;
import Model.Account;
import Utility.Utility;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dat Nguyen
 */
public class View {

    final String PHONE_REGEX = "\\d{10}";
    final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    Utility ul = new Utility();
    AccountController ctrl = new AccountController();

    //Func 1: Create user account
    public void CreateAccount() {
        while (true) {
            //Require to input a user information
            String username = ul.getInput("Please enter username: ");
            String firstName = ul.getInput("Please enter first name:");
            String lastName = ul.getInput("Please enter last name:");
            String password = ul.getInput("Please enter password");
            String confirm = ul.getInput("Please enter confirm password");
            String phoneNumber = ul.getInput("Please enter phone number");
            String email = ul.getInput("Please enter email");

            //Check the valid data
            String error = "";
            //Valid 1: Username must be at least five characters and no spaces. 
            if (!ul.ValidateUsernamePassword(username, 5)) {
                error += "Username must be at least five characters and no spaces.\n";
            }
            //Valid 2: Username is not allowed to duplicate in the database.
            if (ctrl.getAccountByUsername(username) != null) {
                error += "Username is already exist. Try another one.\n";
            }

            //Valid 3: Password must be at least six characters and no spaces.
            if (!ul.ValidateUsernamePassword(password, 6)) {
                error += "Password must be at least six characters and no spaces.\n";
            }
            //Valid 4: Confirm password must equal password.
            if (!confirm.equals(password)) {
                error += "Confirm password must equal password.\n";
            }
            //Valid 5: Phone number must contain 10 numbers.
            if (!ul.ValidateStringUsingRegex(phoneNumber, PHONE_REGEX)) {
                error += "Phone number must contain 10 numbers.\n";
            }
            //Valid 6: Email must follow standard email format.
            if (!ul.ValidateStringUsingRegex(email, EMAIL_REGEX)) {
                error += "Email must follow standard email format.\n";
            }
            //Create new user if no error found.
            if (error.equals("")) {
                Account acc = new Account(username, firstName, lastName, password, phoneNumber, email);
                ctrl.addAccount(acc);
            } else {
                System.out.println(error);
            }

            boolean goBackToMenu = ul.getConfirm("Do you want to go back to menu?");
            if (goBackToMenu) {
                return;
            }
        }
    }

    //Func 2: Check exits user
    public void CheckUserExits() {
        while (true) {
            String username = ul.getInput("Enter username:");
            if (ctrl.getAccountByUsername(username) != null) {
                System.out.println("Exits User");
            } else {
                System.out.println("No User Found!");
            }

            boolean goBackToMenu = ul.getConfirm("Do you want to go back to menu?");
            if (goBackToMenu) {
                return;
            }
        }
    }

    //Func 3: Search user information by name
    public void SearchUserByName() {
        while (true) {
            String search = ul.getInput("Enter name to search:");
            ArrayList<Account> list = ctrl.searchUserByName(search);
            Collections.sort(list);
            if (list == null) {
                System.out.println("Have no any user");
            } else {
                System.out.format("%10s%13s%13s%13s%27s", "Username", "First Name", "Last Name", "Phone", "Email");
                System.out.println("");
                list.forEach((acc) -> {
                    acc.printAccount();
                });
            }

            boolean goBackToMenu = ul.getConfirm("Do you want to go back to menu?");
            if (goBackToMenu) {
                return;
            }
        }
    }

    //Func 4: Update user
    public void UpdateUser() {
        while (true) {
            Account acc = Login();
            //Check login
            if (acc == null) {
                System.out.println("Username does not exist");
            } else {
                //User edit the remaining information
                //Leave blank to keep information
                //User edit first name
                String newFirstName = ul.getInput("Enter new first name:");
                if (newFirstName.trim().equals("")) {
                    newFirstName = acc.getFirstName();
                }
                //User edit last name
                String newLastName = ul.getInput("Enter new last name:");
                if (newLastName.trim().equals("")) {
                    newLastName = acc.getLastName();
                }
                //User edit phone
                String newPhone = ul.getInput("Enter new phone:");
                if (newPhone.trim().equals("")) {
                    newPhone = acc.getPhone();
                } else {
                    //Validate phone
                    while (!ul.ValidateStringUsingRegex(newPhone, PHONE_REGEX)) {
                        System.out.println("Phone number must contain 10 numbers.");
                        newPhone = ul.getInput("Enter new phone:");
                    }
                }
                //User edit email
                String newEmail = ul.getInput("Enter new email:");
                if (newEmail.trim().equals("")) {
                    newEmail = acc.getEmail();
                } else {
                    //Validation email
                    while (!ul.ValidateStringUsingRegex(newEmail, EMAIL_REGEX)) {
                        System.out.println("Email must follow standard email format.");
                        newEmail = ul.getInput("Enter new email:");
                    }
                }
                Account updateAcc = new Account(acc.getUsername(), newFirstName,
                        newLastName, acc.getPassword(), newPhone, newEmail);
                System.out.println(ctrl.updateUserInformation(updateAcc));
            }

            boolean goBackToMenu = ul.getConfirm("Do you want to go back to menu?");
            if (goBackToMenu) {
                return;
            }
        }
    }

    //Func 5: Delete user
    public void DeleteUser() {
        while (true) {
            Account acc = Login();
            //Check login
            if (acc == null) {
                System.out.println("User does not exist");
            } else {
                boolean choice = ul.getConfirm("Do you really want to delete your account?");
                if (choice) {
                    System.out.println(ctrl.deleteUser(acc));
                }
            }

            boolean goBackToMenu = ul.getConfirm("Do you want to go back to menu?");
            if (goBackToMenu) {
                return;
            }
        }
    }

    //Func 6: Save list account to file
    public void SaveListAccountToFile() {
        ctrl.writeListToFile();
    }

    //Func 7: Print list user from file    
    public void PrintListUserFromFile() {
        ArrayList<Account> listAccount = ctrl.readAccountFromFile();
        System.out.format("%10s%13s%13s%13s%27s", "Username", "First Name", "Last Name", "Phone", "Email");
        System.out.println("");
        for (Account acc : listAccount) {
            acc.printAccount();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Account Login() {
        System.out.println("You have to login first");
        String username = ul.getInput("Enter username:");
        String password = ul.getInput("Enter password:");
        Account acc = ctrl.getAccountByUsernameAndPassword(username, password);
        return acc;
    }

}
