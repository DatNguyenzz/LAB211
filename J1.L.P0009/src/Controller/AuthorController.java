/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Author;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dat Nguyen
 */
public class AuthorController {

    final String BREAK =  "#";
    ArrayList<Author> listAuthor = readListAuthorFromFile();

    //Get author by name
    public Author getAuthorByName(String name) {
        for (Author a : listAuthor) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }
    
    //Get author by ID
    public Author getAuthorByID(int id){
        for(Author a : listAuthor){
            if(a.getAuthorID() == id){
                return a;
            }
        }
        return null;
    }

    //Read list author from file
    public ArrayList<Author> readListAuthorFromFile() {
        ArrayList<Author> listA = new ArrayList<>();
        try {
            Path path = Paths.get("author.dat");
            byte[] fileByteContents = Files.readAllBytes(path);
            String fileStringContents = new String(fileByteContents);
            //Get data by line
            String[] lineArray = fileStringContents.split("\n");
            for(String line : lineArray){
                int authorID = Integer.parseInt(line.split(BREAK)[0].trim());
                String authorName = line.split(BREAK)[1].trim();
                listA.add(new Author(authorID, authorName));
            }
        } catch (IOException ex) {
            System.err.println("Error");
            System.out.println(ex);
        }
        return listA;
    }
    
    //Write list author to file
    public void writeListAuthorToFile(){
        try {
            PrintWriter write = new PrintWriter(new FileWriter("author.dat"));
            for(Author a : listAuthor){
                write.println(a.getAuthorID() + BREAK + a.getName());
            }
            write.close();
        } catch (IOException ex) {
            System.err.println("Error");
            System.out.println(ex);
        }
    }
    
    //Delete author by ID
    public boolean deleteAuthorByID(int authorID){
//        for(Author a : listAuthor){
//            if(a.getAuthorID() == authorID){
//                listAuthor.remove(a);
//            }
//        }
        for(int i=0; i<listAuthor.size();i++){
            if(listAuthor.get(i).getAuthorID() == authorID){
                listAuthor.remove(i);
            }
        }
        writeListAuthorToFile();
        //Check delete
        ArrayList<Author> newListAuthor = readListAuthorFromFile();
        for(Author a : newListAuthor){
            if(a.getAuthorID() == authorID){
                return false;
            }
        }
        return true;
    }
}
