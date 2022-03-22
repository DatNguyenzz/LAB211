/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dat Nguyen
 */
public class Book{
    int ISBN;
    String title;
    double price;
    Author author;

    public Book() {
    }

    public Book(int ISBN, String title, double price, Author author) {
        this.ISBN = ISBN;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public void printBookInfo(){
        System.out.format("%-7s%-50s%-20s%-20s", ISBN, title, price, author.getName());
        System.out.println("");
    }
}
