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
public class Account implements Comparable<Account> {

    String username;
    String firstName;
    String lastName;
    String password;
    String phone;
    String email;

    public Account() {
    }

    public Account(String username, String firstName, String lastName, String password, String phone, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", phone=" + phone + ", email=" + email + '}';
    }
    
    public void printAccount(){
        System.out.format("%10s%13s%13s%13s%27s", username, firstName, lastName, phone, email);
        System.out.println("");
    }

    @Override
    public int compareTo(Account o) {
        String firstName = o.getFirstName();
        return firstName.compareToIgnoreCase(this.firstName);
    }
}
