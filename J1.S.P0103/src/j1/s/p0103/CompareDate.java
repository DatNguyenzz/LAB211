/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0103;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dat Nguyen
 */
public class CompareDate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //STEP 1: User enter the first date with [MMM/dd/yyyy] format
        String firstDate = userEnterDate("first date", "MMM/dd/yyyy");

        //STEP 2: User enter the second date with [dd/MM/yyyy] format
        String secondDate = userEnterDate("second date", "dd/MM/yyyy");

        //STEP 3: Compare and display
        compareDate(firstDate, secondDate);

    }

    //User enter date
    private static String userEnterDate(String date, String format) {
        String input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter the " + date + ": ");
            input = sc.next();
            if (checkDateExits(input, format)) {
                break;
            }
        }
        return input;
    }
    
    //Format date from MMM/dd/yyyy to dd/MM/yyyy
    private static int getMonth(String input, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(input);
        } catch (ParseException ex) {
            System.out.println("Wrong date format");
            return 0;
        }
        return date.getMonth()+1;
    }

    //Check user input date exits
    private static boolean checkDateExits(String input, String format) {
        int errorCount = 0;
        if (input.split("/").length != 3) { //Check format
            System.out.println("Wrong format input");
            errorCount++;
        } else {
            try {
                String[] stringSplit = input.split("/");
                int day, month;
                if(format.equals("MMM/dd/yyyy")){
                    day = Integer.parseInt(stringSplit[1]);
                    month = getMonth(input, format);
                }else{
                    day = Integer.parseInt(stringSplit[0]);
                    month = Integer.parseInt(stringSplit[1]);
                }
                //Day can't bigger than 31 and can't be a negative number
                if (day <= 0 || day > 31) {
                    System.out.println("Wrong input for day");
                    errorCount++;
                }
                
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
                System.out.println("Please enter date with format " + format);
                errorCount++;
            }
        }
        return errorCount == 0;
    }

    //Compare date and print result
    private static void compareDate(String first, String second) {
        try {
            SimpleDateFormat firstFormat = new SimpleDateFormat("MMM/dd/yyyy");
            Date firstDate = firstFormat.parse(first);
            SimpleDateFormat secondFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date secondDate = secondFormat.parse(second);
//            //Compare date
//            if(firstDate.after(secondDate)){
//                System.out.println("Date1 is after Date2");
//            }else{
//                System.out.println("Date1 is before Date2");
//            }
            
            int flag = firstDate.compareTo(secondDate);
            if(flag==0){
                System.out.println("Date1 is same as Date2");
            }else if(flag < 0){
                System.out.println("Date1 is before Date2");
            }else{
                System.out.println("Date1 is after Date2");
            }
        } catch (ParseException ex) {
            Logger.getLogger(CompareDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
