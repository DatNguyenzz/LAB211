/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Employee;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dat Nguyen
 */
public class Controller {
    static ArrayList<Employee> listEmployees;

    public Controller() {
        listEmployees = new ArrayList<>();
    }

    
    public ArrayList<Employee> getListEmployees() {
        return listEmployees;
    }
    
    //Add demo employee
    public void addDemo(){
        listEmployees.add(new Employee("firstName0", "lastName0", "phone0", "email0", "address0", new Date(2022, 2, 22), true, 1000, "egency0"));
        listEmployees.add(new Employee("firstName1", "lastName1", "phone1", "email1", "address1", new Date(2022, 2, 22), true, 2000, "egency0"));
        listEmployees.add(new Employee("firstName2", "lastName2", "phone2", "email2", "address2", new Date(2022, 2, 22), true, 4000, "egency0"));
        listEmployees.add(new Employee("firstName3", "lastName3", "phone3", "email3", "address3", new Date(2022, 2, 22), true, 3000, "egency0"));
        listEmployees.add(new Employee("firstName4", "lastName4", "phone4", "email4", "address4", new Date(2022, 2, 22), true, 5000, "egency0"));
    }
    
    //Add employee
    public void addNewEmployee(Employee e){
        listEmployees.add(e);
    }
    
    //Get employee by id
    public Employee getEmployeeById(int id){
        for(Employee e : listEmployees){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }
    
    //Get Employee by name (First Name or Last Name) or a part of name.
    public ArrayList<Employee> getEmployeesByName(String name){
        ArrayList<Employee> searchList = new ArrayList<>();
        for(Employee e : listEmployees){
            if(e.getFirstName().toLowerCase().contains(name.toLowerCase()) 
                    || e.getLastName().toLowerCase().contains(name.toLowerCase())){
                searchList.add(e);
            }
        }
        return searchList;
    }
    
    //Remove employee by id
    public void RemoveEmployeeById(int id){
        for(int i = 0; i < listEmployees.size(); i++){
            if(listEmployees.get(i).getId() == id){
                listEmployees.remove(i);
            }
        }
    }
    
    //Update employee information
    public void updateEmployee(Employee newE){
        for(int i=0; i<listEmployees.size(); i++){
            if(listEmployees.get(i).getId() == newE.getId()){
                listEmployees.get(i).setFirstName(newE.getFirstName());
                listEmployees.get(i).setLastName(newE.getLastName());
                listEmployees.get(i).setPhone(newE.getPhone());
                listEmployees.get(i).setEmail(newE.getEmail());
                listEmployees.get(i).setEgency(newE.getEgency());
                listEmployees.get(i).setDOB(newE.getDOB());
                listEmployees.get(i).setSex(newE.isSex());
                listEmployees.get(i).setSalary(newE.getSalary());
                listEmployees.get(i).setEgency(newE.getEgency());
            }
        }
    }
}
