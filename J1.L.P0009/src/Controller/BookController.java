/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Author;
import Model.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dat Nguyen
 */
public class BookController {
    
    final String BREAK =  "#";

    ArrayList<Book> listBook = readListBookFromFile();

    //Get list book
    public ArrayList<Book> getListBook() {
        return listBook;
    }

    //Get book by ISBN
    public Book getBookByISBN(int ISBN) {
        for (Book b : listBook) {
            if (b.getISBN() == ISBN) {
                return b;
            }
        }
        return null;
    }

    //Add new book
    public boolean addNewBook(Book book) {
        listBook.add(book);
        //Update new list to file
        writeListBookToFile();
        //Check add
        ArrayList<Book> newListBook = readListBookFromFile();
        for (Book b : newListBook) {
            if (book.getISBN() == b.getISBN()) {
                return true;
            }
        }
        return false;
    }

    //Delete a book
    public boolean DeleteBook(Book book) {
        listBook.remove(book);
        //Update to file
        writeListBookToFile();
        //Check delete
        ArrayList<Book> newListBook = readListBookFromFile();
        for (Book b : newListBook) {
            if (book.getISBN() == b.getISBN()) {
                return false;
            }
        }
        return true;
    }
    
    //Update book information
    public boolean updateBook(Book book) {
        for(Book b : listBook){
            if(b.getISBN() == book.getISBN()){
                b.setTitle(book.getTitle());
                b.setPrice(book.getPrice());
                b.setAuthor(book.getAuthor());
            }
        }
        writeListBookToFile();
        //Check update
        ArrayList<Book> newListBook = readListBookFromFile();
        String error = "";
        for(Book b : newListBook){
            if(b.getISBN() == book.getISBN()){
                if(!b.getTitle().equals(book.getTitle())){
                    error += "Update title fail!\n";
                }if(!(b.getPrice()==book.getPrice())){
                    error += "Update price fail!\n";
                }if(!(b.getAuthor().getAuthorID()==book.getAuthor().getAuthorID())){
                    error += "Update authorID fail!\n";
                }if(!b.getAuthor().getName().equals(book.getAuthor().getName())){
                    error += "Update author name fail!\n";
                }
            }
        }
        return error == "";
    }

    //Read list book from file
    public ArrayList<Book> readListBookFromFile() {
        ArrayList<Book> listB = new ArrayList<>();
        try {
            Path path = Paths.get("book.dat");
            byte[] fileByteContents = Files.readAllBytes(path);
            String fileStringContents = new String(fileByteContents);
            //Get data by line
            String[] lineArray = fileStringContents.split("\n");
            for (String line : lineArray) {
                Book book = new Book();
//                System.out.println(line.split(BREAK)[0].trim());
                book.setISBN(Integer.parseInt(line.split(BREAK)[0].trim()));
//                System.out.println(line.split(BREAK)[1].trim());
                book.setTitle(line.split(BREAK)[1].trim());
//                System.out.println(line.split(BREAK)[2].trim());
                book.setPrice(Double.parseDouble(line.split(BREAK)[2].trim()));
//                System.out.println(line.split(BREAK)[3].trim());
                String authorIDString = line.split(BREAK)[3].trim();
                int authorIDINT = Integer.parseInt(authorIDString);
                Author author = new AuthorController().getAuthorByID(authorIDINT);
                if (author == null) {
                    author.setName("Error");
                }
                book.setAuthor(author);
                listB.add(book);
            }
        } catch (IOException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NumberFormatException ex){
            ex.printStackTrace();
        }
        return listB;
    }

    //Write list book to file
    public void writeListBookToFile() {
        try {
            PrintWriter write = new PrintWriter(new FileWriter("book.dat"));
            for (Book b : listBook) {
                write.println(b.getISBN() + BREAK + b.getTitle() + BREAK
                        + b.getPrice() + BREAK + b.getAuthor().getAuthorID());
            }
            write.close();
        } catch (IOException ex) {
            System.err.println("Error");
            System.out.println(ex);
        }
    }

    //Search book by name
    public ArrayList<Book> searchBookByName(String search) {
        ArrayList<Book> listSearch = new ArrayList<>();
        for(Book b : listBook){
            if(b.getTitle().toLowerCase().contains(search.toLowerCase())){
                listSearch.add(b);
            }
        }
        return listSearch;
    }

    //Search book by author name
    public ArrayList<Book> searchBookByAuthorName(String search) {
        ArrayList<Book> listSearch = new ArrayList<>();
        for(Book b : listBook){
            if(b.getAuthor().getName().equals(search)){
                listSearch.add(b);
            }
        }
        return listSearch;
    }
}
