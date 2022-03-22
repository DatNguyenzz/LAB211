/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Task;
import Model.TypeOfTask;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dat Nguyen
 */
public class Controller {

    ArrayList<Task> listTask = new ArrayList<>();
    ArrayList<TypeOfTask> listType = new ArrayList<>();

    public Controller() {
        listType.add(new TypeOfTask(1, "Code"));
        listType.add(new TypeOfTask(2, "Test"));
        listType.add(new TypeOfTask(3, "Design"));
        listType.add(new TypeOfTask(4, "Review"));
    }
    
    public ArrayList<Task> getDataTask() {
        return listTask;
    }

    public ArrayList<TypeOfTask> getListType() {
        return listType;
    }
    
    //Get task by id
    public Task getTaskByID(int taskID) {
        for (Task t : listTask) {
            if (t.getID() == taskID) {
                return t;
            }
        }
        return null;
    }

    //Get type of task by id
    public TypeOfTask getTaskTypeByID(int typeID) {
        for (TypeOfTask type : listType) {
            if (type.getID() == typeID) {
                return type;
            }
        }
        return null;
    }
    
    //Add new task to list
    public int addTask(String requirementName, Date date, double planFrom, 
            double planTo, String assignee, String reviewer, int typeOfTaskID){
        Task task = new Task();
        TypeOfTask taskType = getTaskTypeByID(typeOfTaskID);
        task.setRequirmentName(requirementName);
        task.setAssignee(assignee);
        task.setDate(date);
        task.setPlanFrom(planFrom);
        task.setPlanTo(planTo);
        task.setReviewer(reviewer);
        task.setTypeOfTask(taskType);
        listTask.add(task);
        return task.getID();
    }
    
    //Delete task by ID
    public void deleteTask(int taskID){
        for(int i=0; i<listTask.size(); i++){
            if (listTask.get(i).getID() == taskID) {
                listTask.remove(i);
            }
        }
    }
}
