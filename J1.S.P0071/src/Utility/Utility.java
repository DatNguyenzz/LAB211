/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    //Get input int
    public int getInt(String msg, int min, int max) {
        while (true) {
            System.out.println(msg);
            try {
                int in = Integer.parseInt(sc.nextLine());
                if (in > max || in < min) {
                    System.out.println("Input out of range");
                } else {
                    return in;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input for number");
            }
        }
    }

    //Get input double
    public double getDouble(String msg, double min, double max) {
        while (true) {
            System.out.println(msg);
            try {
                double in = Double.parseDouble(sc.nextLine());
                if (in > max || in < min) {
                    System.out.println("Input out of range");
                } else {
                    return in;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input for number");
            }
        }
    }

    //Get input string from user
    public String getInput(String message) {
        System.out.println(message);
        return sc.nextLine();
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
    public boolean validateStringUsingRegex(String in, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(in);
        return matcher.matches();
    }

    //Get date
    public Date getDate(String msg, String err, String format) {
        while (true) {
            System.out.println(msg);
            String in = sc.nextLine();
            if (checkDateFormat(in, format)) {
                try {
                    Date date = new SimpleDateFormat(format).parse(in);
                    return date;
                } catch (ParseException ex) {
                    System.err.println(err);
                }
            } else {
                System.err.println(err);
            }
        }
    }

    //Check date
    public boolean checkDateFormat(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
