/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Account;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dat Nguyen
 */
public class AccountController {

    ArrayList<Account> listAccount = readAccountFromFile();

    //Get account by username
    public Account getAccountByUsername(String username) {
        for (Account acc : listAccount) {
            if (acc.getUsername().equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }

    //Add new account
    public void addAccount(Account account) {
        account.setPassword(encryptPassword(account.getPassword()));
        listAccount.add(account);
    }

    //Read list account from file
    public ArrayList<Account> readAccountFromFile() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            File file = new File("user.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] accountData = line.split("</>");
                Account acc = new Account(accountData[0], accountData[1],
                        accountData[2], accountData[3], accountData[4], accountData[5]);
                list.add(acc);
            }
        } catch (IOException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Write to file
    public void writeListToFile() {
        try {
            FileWriter fw = new FileWriter("user.txt");
            String list = "";
            for (Account acc : listAccount) {
                list += acc.getUsername() + "</>"
                        + acc.getFirstName() + "</>"
                        + acc.getLastName() + "</>"
                        + acc.getPassword() + "</>"
                        + acc.getPhone() + "</>"
                        + acc.getEmail() + "\n";
            }
            fw.write(list);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Search user by name
    public ArrayList<Account> searchUserByName(String search) {
        ArrayList<Account> list = new ArrayList<>();
        for (Account acc : listAccount) {
            if (acc.getFirstName().contains(search)
                    || acc.getLastName().contains(search)) {
                list.add(acc);
            }
        }
        return list;
    }

    //Search account by username and password
    public Account getAccountByUsernameAndPassword(String username, String password) {
        password = encryptPassword(password);
        for (Account acc : listAccount) {
            if (acc.getUsername().equals(username)) {
                if (acc.getPassword().equals(password)) {
                    return acc;
                } else {
                    return null;
                }

            }
        }
        return null;
    }

    //Update user information
    public String updateUserInformation(Account acc) {
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.get(i).getUsername().equals(acc.getUsername())) {
                listAccount.get(i).setFirstName(acc.getFirstName());
                listAccount.get(i).setLastName(acc.getLastName());
                listAccount.get(i).setPhone(acc.getPhone());
                listAccount.get(i).setEmail(acc.getEmail());
            }
        }
        writeListToFile();
        //Check update
        ArrayList<Account> checkList = readAccountFromFile();
        String error = "";
        for (Account a : checkList) {
            if (a.getUsername().equals(acc.getUsername())) {
                if (!a.getFirstName().equals(acc.getFirstName())) {
                    error += "Update first name fail.\n";
                }
                if (!a.getLastName().equals(acc.getLastName())) {
                    error += "Update last name fail.\n";
                }
                if (!a.getPhone().equals(acc.getPhone())) {
                    error += "Update phone fail.\n";
                }
                if (!a.getEmail().equals(acc.getEmail())) {
                    error += "Update email fail.\n";
                }
                if (error.equals("")) {
                    return "Update success.";
                }
            }
        }
        System.out.println(error);
        return error;
    }

    //Delete user
    public String deleteUser(Account acc) {
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.get(i).getUsername().equals(acc.getUsername())) {
                listAccount.remove(i);
            }
        }
        writeListToFile();
        //Check delete
        ArrayList<Account> checkList = readAccountFromFile();
        for (Account a : checkList) {
            if (a.getUsername().equals(acc.getUsername())) {
                return "Delete fail.";
            }
        }
        return "Delete success.";
    }

    //Password encryption
    public String encryptPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            return password;
        }
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

}
