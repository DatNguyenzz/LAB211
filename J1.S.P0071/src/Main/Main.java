/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Utility.Utility;
import View.View;

/**
 *
 * @author Dat Nguyen
 */
public class Main {

    public static void main(String[] args) {
        View view = new View();
        Utility utility = new Utility();
        while (true) {
            //Func 1: Display a menu and ask user to select an option
            view.displayMENU();
            //Func 2: Perform function based on the selected option
            int choice = utility.getInt("Enter your choice", 1, 4);
            switch (choice) {
                //Option 1: Add task
                case 1:
                    view.addTask();
                    break;
                //Option 2: Delete task
                case 2:
                    view.deleteTask();
                    break;
                //Option 3: Show task
                case 3:
                    view.showTask();
                    break;
                //Option 4: Exit the program
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }
}
