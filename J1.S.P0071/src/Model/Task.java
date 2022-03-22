/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dat Nguyen
 */
public class Task {
    private static int i = 0;
    private int ID;
    private String requirmentName;
    private Date date;
    private double planFrom;
    private double planTo;
    private String assignee;
    private String reviewer;
    private TypeOfTask typeOfTask;

    public Task() {
        i++;
        ID = i;
    }

    public Task(String requirmentName, Date date, double planFrom, double planTo, String assignee, String reviewer, TypeOfTask typeOfTask) {
        i++;
        this.ID = i;
        this.requirmentName = requirmentName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assignee = assignee;
        this.reviewer = reviewer;
        this.typeOfTask = typeOfTask;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRequirmentName() {
        return requirmentName;
    }

    public void setRequirmentName(String requirmentName) {
        this.requirmentName = requirmentName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPlanFrom() {
        return planFrom;
    }

    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    public double getPlanTo() {
        return planTo;
    }

    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public void setTypeOfTask(TypeOfTask typeOfTask) {
        this.typeOfTask = typeOfTask;
    }
    
    public void printTask(){
        double time = planTo - planFrom;
        System.out.format("%-5s%-15s%-15s%-15s%-15s%-10s%-10s", 
            ID, requirmentName, typeOfTask.getName(), formatDate(), time, assignee, reviewer);
        System.out.println("");
    }
    
    public String formatDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);
        String strDate = formatter.format(this.date);
        return strDate;
    }
    
}
