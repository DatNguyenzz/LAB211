/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Entity.Employee;
import Utility.Utility;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dat Nguyen
 */
public class View {

    final String PHONE_REGEX = "\\d{10}";
    final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    Utility ul = new Utility();
    Controller ctrler = new Controller();

    //Func 1: Add employees
    public void AddEmployee() {
        System.out.println("ADD EMPLOYEES");
        String firstName = ul.getInput("Please enter first name:");
        String lastName = ul.getInput("Please enter last name:");
        String phoneNumber = ul.getInput("Please enter phone number");
        String email = ul.getInput("Please enter email");
        String address = ul.getInput("Please enter address: ");
        String DOB = ul.getInput("Please enter DOB: (dd/mm/yyyy)");
        String sex = ul.getInput("Please enter sex:\n"
                + "1. Male\n"
                + "2. Female");
        String salaryStr = ul.getInput("Please enter salary:");
        String egency = ul.getInput("Please input egency:");

        //Check input
        String error = "";
        //Check phone number must contain 10 numbers.
        if (!ul.ValidateStringUsingRegex(phoneNumber, PHONE_REGEX)) {
            error += "Phone number must contain 10 numbers.\n";
        }
        //Check email must follow standard email format.
        if (!ul.ValidateStringUsingRegex(email, EMAIL_REGEX)) {
            error += "Email must follow standard email format.\n";
        }
        //Check DOB
        if (!ul.checkDateExits(DOB)) {
            error += "Date not exits";
        }else{
            if(!ul.checkAge(ul.formatStringToDate(DOB))){
                error += "Employee to young";
            }
        }
        //Check sex
        if (!"1".equals(sex) && !"2".equals(sex)) {
            error += "Wrong input for sex, enter 1 or 2";
        }
        //Check salary must be a number
        double salary = 0;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException ex) {
            error += "Wrong input  for salary, enter a number";
        }

        //Create new employee no error found.
        if ("".equals(error)) {
            Employee e = new Employee();
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setPhone(phoneNumber);
            e.setEmail(email);
            e.setAddress(address);
            e.setDOB(ul.formatStringToDate(DOB));
            e.setSex("1".equals(sex));
            e.setSalary(salary);
            e.setEgency(egency);
            ctrler.addNewEmployee(e);
        }else{
            System.out.println(error);
        }
    }

    //Func 2: Update employee
    public void UpdateEmployee() {
        System.out.println("UPDATE EMPLOYEE");
        try {
            int id = Integer.parseInt(ul.getInput("Please enter employee id to update"));
            Employee e = ctrler.getEmployeeById(id);
            //Check if employee exists -> Update employee
            if (e != null) {
                String firstName = ul.getInput("Please enter first name:");
                String lastName = ul.getInput("Please enter last name:");
                String phoneNumber = ul.getInput("Please enter phone number");
                String email = ul.getInput("Please enter email");
                String address = ul.getInput("Please enter address: ");
                String DOB = ul.getInput("Please enter DOB: (dd/mm/yyyy)");
                String sex = ul.getInput("Please enter sex:\n"
                        + "1. Male\n"
                        + "2. Female");
                String salaryStr = ul.getInput("Please enter salary:");
                String egency = ul.getInput("Please input egency:");

                //Check input
                String error = "";
                //Check phone number must contain 10 numbers.
                if (!ul.ValidateStringUsingRegex(phoneNumber, PHONE_REGEX)) {
                    error += "Phone number must contain 10 numbers.\n";
                }
                //Check email must follow standard email format.
                if (!ul.ValidateStringUsingRegex(email, EMAIL_REGEX)) {
                    error += "Email must follow standard email format.\n";
                }
                //Check DOB
                if (!ul.checkDateExits(DOB)) {
                    error += "Date not exits";
                }
                //Check sex
                if (!"1".equals(sex) && !"2".equals(sex)) {
                    error += "Wrong input for sex, enter 1 or 2";
                }
                //Check salary must be a number
                double salary = 0;
                try {
                    salary = Double.parseDouble(salaryStr);
                } catch (NumberFormatException ex) {
                    error += "Wrong input  for salary, enter a number";
                }
                //Update employee if no error found
                if ("".equals(error)) {
                    e.setFirstName(firstName);
                    e.setLastName(lastName);
                    e.setPhone(phoneNumber);
                    e.setEmail(email);
                    e.setAddress(address);
                    e.setDOB(ul.formatStringToDate(DOB));
                    e.setSex("1".equals(sex));
                    e.setSalary(salary);
                    e.setEgency(egency);
                    ctrler.updateEmployee(e);
                }else{
                    System.out.println(error);
                }
            } else {//If employee doesn't exist -> Return to menu
                System.out.println("Employee does not exist!");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Wrong input for id, please enter a number");
        }

    }

    //Func 3: search employee
    public void SearchEmployee() {
        String search = ul.getInput("Enter name to search:");
        ArrayList<Employee> searchList = ctrler.getEmployeesByName(search);
        if (searchList == null) {
            System.out.println("Employee does not exist!");
        } else {
            for (Employee e : searchList) {
                System.out.println(e.toString());
            }
        }
    }

    //Func 4: Remove employee
    public void RemoveEmployee() {
        String searchId = ul.getInput("Enter employee id to remove:");
        try {
            int id = Integer.parseInt(searchId);
            Employee searchEmp = ctrler.getEmployeeById(id);
            if (searchEmp != null) {
                if (ul.getConfirm("Are you sure to delete this employee?")) {
                    ctrler.RemoveEmployeeById(id);
                }
            } else {
                System.out.println("Employee does not exist!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter a number for employee id");
        }
    }

    //Func 5: Sort employees by salary
    public void SortEmployeesBySalary() {
        ArrayList<Employee> listEmp = ctrler.getListEmployees();
        System.out.println("Sort employee by salary");
        System.out.format("%5s%13s%13s%15s%20s%10s%13s%7s%8s%8s", "ID", "First Name", "Last Name", "Phone", "Email", "Address", "DOB", "Sex", "Salary", "Egency");
        System.out.println("");
        Collections.sort(listEmp);
        for(Employee e : listEmp){
            e.printEmp();
        }
    }

}
