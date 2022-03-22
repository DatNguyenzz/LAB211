/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.Controller;
import View.View;
import java.util.Scanner;

/**
 *
 * @author Dat Nguyen
 */
public class Main {
    public static void main(String[] args) {
        View view = new View();
//        new Controller().addDemo(); //Add demo
        while(true){
            switch(getChoice()){
                //Func 1: Add employees
                case 1:
                    view.AddEmployee();
                    break;
                //Func 2: Update employees
                case 2: 
                    view.UpdateEmployee();
                    break;
                //Func 3: Remove employees
                case 3:
                    view.RemoveEmployee();
                    break;
                //Func 4: Search employees
                case 4:
                    view.SearchEmployee();
                    break;
                //Func 5: Sort employees by salary
                case 5:
                    view.SortEmployeesBySalary();
                    break;
                //Other input -> Quit program
                default:
                    System.exit(0);
            }
        }
    }
    
    public static int getChoice(){
        System.out.println("1. Add employees");
        System.out.println("2. Update employees");
        System.out.println("3. Remove employees");
        System.out.println("4. Search employees");
        System.out.println("5. Sort employees by salary");
        System.out.println("Others- Exit");
        return Integer.parseInt(new Scanner(System.in).next());
    }
    
}
