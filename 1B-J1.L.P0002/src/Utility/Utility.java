/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author Dat Nguyen
 */
public class Utility {

    Scanner sc = new Scanner(System.in);

    //Username must be at least five characters and no spaces.
    //Password must be at least six characters and no spaces.
    public boolean ValidateUsernamePassword(String in, int size) {
        int errorCount = 0;
        if (in.length() < size) {
            errorCount++;
        }
        if (in.contains(" ")) {
            errorCount++;
        }
        return errorCount == 0;
    }

    //Validate with regex
    public boolean ValidateStringUsingRegex(String in, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(in);
        return matcher.matches();
    }

    //Get input from user
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

}
