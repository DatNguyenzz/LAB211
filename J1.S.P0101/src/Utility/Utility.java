/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dat Nguyen
 */
public class Utility {

    Scanner sc = new Scanner(System.in);

    //Get input from user
    public String getInput(String message) {
        while (true) {
            System.out.println(message);
            String in = sc.nextLine();
            if (!"".equals(in)) {
                return in;
            }
            System.out.println("Wrong input");
        }

    }

    //Get confirm option
    public boolean getConfirm(String message) {
        while (true) {
            String input = getInput(message + "\nY/N?").trim().toUpperCase();
            switch (input) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Wrong input, Enter Y or N!");
                    break;
            }
        }
    }

    //Validate with regex
    public boolean ValidateStringUsingRegex(String in, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(in);
        return matcher.matches();
    }

    //Validate date
    public boolean checkDateExits(String input) {
        int errorCount = 0;
        String[] stringSplit = input.split("/");
        if (stringSplit.length != 3) { //Check format
            System.out.println("Wrong format input");
            errorCount++;
        } else {
            try {
                int day = Integer.parseInt(stringSplit[0]);
                //Day can't bigger than 31 and can't be a negative number
                if (day <= 0 || day > 31) {
                    System.out.println("Wrong input for day");
                    errorCount++;
                }
                int month = Integer.parseInt(stringSplit[1]);
                //Month can't bigger than 12 and can't be a negative number
                if (month <= 0 || month > 12) {
                    System.out.println("Wrong input for month");
                    errorCount++;
                }
                int year = Integer.parseInt(stringSplit[2]);
                //Year can't be a negative number
                if (year <= 0) {
                    System.out.println("Wrong input for year");
                    errorCount++;
                }
                //Check day of month
                if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                    System.out.println("This month can't have 31 days");
                    errorCount++;
                } else if (month == 2 && day > 29) {
                    System.out.println("February can't have more than 29 days");
                    errorCount++;
                }
                //Check leap year
                if (year % 4 != 0 && month == 2 && day == 29) {
                    System.out.println("Wrong date, " + year + " is not leap year");
                    errorCount++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Day, Month, Year must be a number");
                errorCount++;
            }
        }
        return errorCount == 0;
    }

    //Format string to date
    public Date formatStringToDate(String in){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(in);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //Check employee min age
    public boolean checkAge(Date date){
        int DOByear = date.getYear();
        int thisYear = new java.util.Date().getYear();
        if((thisYear - DOByear) > 18){
            return true;
        }else{
            return false;
        }
              
    }
}
