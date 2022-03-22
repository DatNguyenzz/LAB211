/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import View.View;
import java.util.Scanner;

/**
 *
 * @author Dat Nguyen
 */
public class Main {

    public static void main(String[] args) {
        View view = new View();
        while (true) {
            switch (getChoice()) {
                //Func 1: Create user account 
                case 1:
                    view.CreateAccount();
                    break;
                //Func 2: Check exits user    
                case 2:
                    view.CheckUserExits();
                    break;
                //Func 3: Search user information by name
                case 3:
                    view.SearchUserByName();
                    break;
                //Func 4: Update user
                case 4:
                    view.UpdateUser();
                    break;
                //Func 5: Delete user
                case 5:
                    view.DeleteUser();
                    break;
                //Func 6: Save list account to file
                case 6:
                    view.SaveListAccountToFile();
                    break;
                //Func 7: Print list user from file    
                case 7:
                    view.PrintListUserFromFile();
                    break;
                //Other input -> Quit program
                default:
                    System.exit(0);
            }

        }
    }

    public static int getChoice() {
        System.out.println("1. Create user account.");
        System.out.println("2. Check exits user.");
        System.out.println("3. Search user information by name.");
        System.out.println("4. Update user.");
        System.out.println("5. Delete user.");
        System.out.println("6. Save account to file.");
        System.out.println("7. Print list user from file.");
        System.out.println("Others- Quit.");
        return Integer.parseInt(new Scanner(System.in).next());
    }
}
