package com.example.todoapp.Model;

public class ToDoModel {

    // Private fields to store task details
    private String task;
    private int id, status;

    // Getter method for retrieving the task description
    public String getTask() {
        return task;
    }

    // Setter method for setting the task description
    public void setTask(String task) {
        this.task = task;
    }

    // Getter method for retrieving the task ID
    public int getId() {
        return id;
    }

    // Setter method for setting the task ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for retrieving the task status
    public int getStatus() {
        return status;
    }

    // Setter method for setting the task status
    public void setStatus(int status) {
        this.status = status;
    }
}
