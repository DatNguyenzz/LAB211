/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Task;
import Utility.Utility;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dat Nguyen
 */
public class View {
    Utility utility = new Utility();
    Controller controller = new Controller();
    //Display MENU
    public void displayMENU(){
        System.out.println("============= Task program =============");
        System.out.println("1. Add task");
        System.out.println("2. Delete task");
        System.out.println("3. Display task");
        System.out.println("4. Exit");
    }

    //Option 1: add task
    public void addTask() {
        System.out.println("---------------- Add Task ----------------");
        String requirementName = utility.getInput("Enter requirement name:");
        int taskType = utility.getInt("Enter task type:", 1, 4);
        Date date = utility.getDate("Enter date:", "Wrong date format", "dd-MM-yyyy");
        double planFrom;
        double planTo;
        while(true){
            planFrom = utility.getDouble("Enter plan from", 8, 17.5);
            planTo = utility.getDouble("Enter plan to", 8, 17.5);
            if(planFrom < planTo){
                break;
            }
        }
        String assignee = utility.getInput("Enter assignee:");
        String reviewer = utility.getInput("Enter reviewer:");
        
        int id = controller.addTask(requirementName, date, planFrom, planTo, assignee, 
                reviewer, taskType);
        Task task = controller.getTaskByID(id);
        System.out.println("------------------------------ Task ------------------------------");
        System.out.format("%-5s%-15s%-15s%-15s%-15s%-10s%-10s",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        System.out.println();
        task.printTask();
        System.out.println();
    }

    //Option 2: delete task
    public void deleteTask() {
        System.out.println("---------------- Del Task ----------------");
        int taskID = utility.getInt("Enter ID:", 0, Integer.MAX_VALUE);
        Task task = controller.getTaskByID(taskID);
        if(task == null){
            System.err.println("Task not found");
        }else{
            controller.deleteTask(taskID);
        }
        
    }

    //Option 3: show task
    public void showTask() {
        ArrayList<Task> listTask = controller.getDataTask();
        System.out.println("------------------------------ Task ------------------------------");
        System.out.format("%-5s%-15s%-15s%-15s%-15s%-10s%-10s",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        System.out.println("");
        for(Task t : listTask){
            t.printTask();
        }
        System.out.println();
    }
}
