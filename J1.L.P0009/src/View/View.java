/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AuthorController;
import Controller.BookController;
import Model.Author;
import Model.Book;
import Utility.Utility;
import java.util.ArrayList;

/**
 *
 * @author Dat Nguyen
 */
public class View {

    Utility ul = new Utility();
    BookController bookCtrl = new BookController();
    AuthorController authorCtrl = new AuthorController();
    final String FORMAT_PRINT = "%-7s%-50s%-20s%-20s";

    //Display MENU
    public static void displayMenu() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Show the book list");
        System.out.println("2. Add new book");
        System.out.println("3. Update new book");
        System.out.println("4. Delete book");
        System.out.println("5. Search book");
        System.out.println("6. Delete author");
        System.out.println("7. Exit");
    }

    //Func 1: Dislay the book list
    public void displayBookList() {
        ArrayList<Book> listBook = bookCtrl.getListBook();
        System.out.format(FORMAT_PRINT, "ISBN", "Title", "Price", "Author Name");
        System.out.println("");
        for (Book b : listBook) {
            b.printBookInfo();
        }
    }

    //Func 2: Add new book
    public void addNewBook() {
        do {
            int ISBN;
            while (true) {
                ISBN = ul.getInt("Enter ISBN:", 1, Integer.MAX_VALUE);
                //Validate: BookID can not duplicate
                if (bookCtrl.getBookByISBN(ISBN) == null) {
                    break;
                } else {
                    System.out.println("ISBN is already exist!");
                }
            }
            String title = ul.getInput("Enter title:");
            double price = ul.getDouble("Enter price", 0, Double.MAX_VALUE);
            Author author;
            while (true) {
                author = authorCtrl.getAuthorByName(ul.getInput("Enter author name:"));
                //Validate: Author must be existed in the author list
                if (author != null) {
                    break;
                } else {
                    System.out.println("Author does not exist!");
                }
            }
            Book book = new Book(ISBN, title, price, author);
            if (bookCtrl.addNewBook(book)) {
                System.out.println("Add new book to database successful!");
            } else {
                System.err.println("Add new book to database fail!!!");
            }
        } while (ul.getConfirm("Do you want to continue add new book?"));
    }

    //Func 3: Update book
    public void updateBook() {
        int ISBN = ul.getInt("Enter ISBN to update:", 0, Integer.MAX_VALUE);
        Book book = bookCtrl.getBookByISBN(ISBN);
        if (book == null) {
            System.err.println("Book does not exist");
        } else {
            //User edit the remaining information
            //Leave blank to keep information

            //User edit title
            String title = ul.getInput("Enter title:");
            if (!title.trim().equals("")) {
                book.setTitle(title);
            }

            //User edit price
            double price;
            String getPrice = ul.getInput("Enter price:");
            if (!getPrice.trim().equals("")) {
                try {
                    price = Double.parseDouble(getPrice);
                } catch (NumberFormatException ex) {
                    System.out.println("Wrong format input");
                    price = ul.getDouble("Enter price", Double.MIN_VALUE, Double.MAX_VALUE);
                }
                book.setPrice(price);

            }

            //User edit author
            String authorName;
            while (true) {
                authorName = ul.getInput("Enter author name:");
                if (!authorName.trim().equals("")) {
                    Author author = authorCtrl.getAuthorByName(authorName);
                    if (author == null) {
                        System.err.println("Author does not exist!");
                    } else {
                        book.setAuthor(author);
                        break;
                    }
                } else {
                    break;
                }
            }

            //Update book
            if (bookCtrl.updateBook(book)) {
                System.out.println("Update new book to database successful!");
            } else {
                System.err.println("Update new book to database fail!!!");
            }
        }
    }

    //Func 4: Delete Book by ISBN
    public void deleteBook() {
        int ISBN = ul.getInt("Enter ISBN:", 1, Integer.MAX_VALUE);
        Book book = bookCtrl.getBookByISBN(ISBN);
        if (book == null) {
            System.out.println("Book does not exist!");
        } else {
            if (ul.getConfirm("Do you want to delete this book?")) {
                if (bookCtrl.DeleteBook(book)) {
                    System.out.println("Delete book in database successful!");
                } else {
                    System.err.println("Delete book in database fail!!!");
                }
            }
        }
    }

    //Func 5: Search book
    public void searchBook() {
        System.out.println("1. Search by book name");
        System.out.println("2. Search by author name");
        int choice = ul.getInt("Enter your choice:", 1, 2);
        switch(choice){
            case 1: 
                searchByName();
                break;
            case 2:
                searchByAuthor();
                break;
        }
    }
    
    //Func 5.1: Search by book name
    public void searchByName(){
        String search = ul.getInput("Enter book name to search:");
        ArrayList<Book> listBook = bookCtrl.searchBookByName(search);
        if (listBook.isEmpty()) {
            System.err.println("Search not found");
        } else {
            System.out.format(FORMAT_PRINT, "ISBN", "Title", "Price", "Author Name");
            System.out.println("");
            for (Book b : listBook) {
                b.printBookInfo();
            }
        }
    }
    
    //Func 5.2: Search by author name
    private void searchByAuthor() {
        String search = ul.getInput("Enter author name to search:");
        Author author = authorCtrl.getAuthorByName(search);
        if(author == null){
            System.err.println("Author not found!");
        }else{
            ArrayList<Book> listBook = bookCtrl.searchBookByAuthorName(search);
            System.out.format(FORMAT_PRINT, "ISBN", "Title", "Price", "Author Name");
            System.out.println("");
            for (Book b : listBook) {
                b.printBookInfo();
            }
        }
        
    }

    //Func 6: Delete author
    public void deleteAuthor() {
        int authorID = Integer.parseInt(ul.getInput("Enter author ID to delete:"));
        Author author = authorCtrl.getAuthorByID(authorID);
        if(author == null){
            System.out.println("Author not found!");
        }else{
            ArrayList<Book> listBook = bookCtrl.searchBookByAuthorName(author.getName());
            if(listBook.isEmpty()){
                if(authorCtrl.deleteAuthorByID(authorID)){
                    System.out.println("Delete author in database successful!");
                } else {
                    System.err.println("Delete author in database fail!!!");
                }
            }else{
                System.out.println("This author has a book in the store, you cannot delete this author.");
            }
        }
    }
    

    
}
