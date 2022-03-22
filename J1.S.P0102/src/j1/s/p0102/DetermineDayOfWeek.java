/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0102;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dat Nguyen
 */
public class DetermineDayOfWeek {

    public static void main(String[] args) {
        String input;
        //Loop until user enter correct format
        while (true) {
            //STEP 1: user enter date with [dd/mm/yyyy] format
            input = userEnterDate();
            //STEP 2: check user input date exits
            //If date exits then break loop
            if (checkDateExits(input)) {
                break;
            }
        }
        //STEP 3: Determine day of week with input date
        determineDayOfWeek(input);

    }

    //User enter date with [dd/mm/yyyy] format
    private static String userEnterDate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter date with format [dd/mm/yyyy]: ");
        String input = sc.next();
        return input;
    }

    //Check user input date exits
    private static boolean checkDateExits(String input) {
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

    //Determine day of week and print result
    private static void determineDayOfWeek(String input) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(input);
            SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
            String dayOfWeek = sdf2.format(date);
            System.out.println("Your day is " + dayOfWeek);
        } catch (ParseException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
