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
        View view = new View ();
        while(true){
            view.displayMenu();
            switch(new Utility.Utility().getInt("Your choice: ", 1, 7)){
                //Func 1: Show the book list
                case 1: 
                    view.displayBookList();
                    break;
                //Func 2: Add new book
                case 2:
                    view.addNewBook();
                    break;
                //Func 3: Update book
                case 3:
                    view.updateBook();
                    break;
                //Func 4: Delete book
                case 4:
                    view.deleteBook();
                    break;
                //Func 5: Search book
                case 5:
                    view.searchBook();
                    break;
                //Func 6: Delete author
                case 6:
                    view.deleteAuthor();
                    break;
                //Func 7: Exit program
                case 7:
                    System.exit(0);
                    break;
            }
        }
        
    }
    
}
