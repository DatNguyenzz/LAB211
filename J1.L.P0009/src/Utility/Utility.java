/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.Scanner;
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
                if(in > max || in < min){
                    System.out.println("Input out of range");
                }else{
                    return in;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input for number");
            }
        }
    }
    
    //Get input double
    public double getDouble(String msg, double min, double max){
        while (true) {
            System.out.println(msg);
            try {
                double in = Double.parseDouble(sc.nextLine());
                if(in > max || in < min){
                    System.out.println("Input out of range");
                }else{
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
    public boolean getConfirm(String message){
        while(true){
            String input = getInput(message + "\nY/N?").trim().toUpperCase();
            switch(input){
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
}
