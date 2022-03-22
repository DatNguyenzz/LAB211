/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utility.Utility;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dat Nguyen
 */
public class Employee implements Comparable<Employee> {

    int id;
    String firstName;
    String lastName;
    String phone;
    String email;
    String address;
    Date DOB;
    boolean sex;
    double salary;
    String egency;

    static int i = 0;

    public Employee() {
        i++;
        id = i;
    }

    public Employee(String firstName, String lastName, String phone, String email, String address, Date DOB, boolean sex, double salary, String egency) {
        i++;
        this.id = i;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.DOB = DOB;
        this.sex = sex;
        this.salary = salary;
        this.egency = egency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEgency() {
        return egency;
    }

    public void setEgency(String egency) {
        this.egency = egency;
    }
    
    public String formatDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(this.DOB);
        return strDate;
    }
    
    public String getSexString(){
        if(this.sex){
            return "Male";
        }else{
            return "Female";
        }
    }
    
    public void printEmp(){
        System.out.format("%5s%13s%13s%15s%20s%10s%13s%7s%8s%8s", id, firstName, lastName, phone, email, address, formatDate(), getSexString(), salary, egency);
        System.out.println("");
    }

    @Override
    public String toString() {
        return id + " \t " + firstName + " \t " + lastName + " \t " + phone + " \t " + email + " \t " + address + " \t " + formatDate() + " \t " + getSexString() + " \t " + salary + " \t " + egency;
    }

    @Override
    public int compareTo(Employee o) {
        return (int) (o.getSalary() - this.getSalary());
    }
}
