package com.example.taskmanagement.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import com.example.taskmanagement.model.Status;


@Component // Indicate that this is a Spring-managed component
@Entity // Hibernate will create Table task, since we want to create a table in the database using this class
public class Task {
    @Id //int taskId is the primary key
    private int taskId;
    private String taskName;
    private String taskDescription;
    //private String taskStatus; // should be enum, PENDING, IN_PROGRESS, DONE
    @Enumerated(EnumType.STRING)
    private Status taskStatus;
    // REQUIRED BY JPA
    public Task(){

    }
    //is this constructor necessary?
    public Task(int taskId, String taskName, String taskDescription, Status taskStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        //this.taskStatus = taskStatus;
        if (taskStatus == Status.PENDING) {
            this.taskStatus = Status.PENDING; 
        }
        else if (taskStatus == Status.IN_PROGRESS) {
            this.taskStatus = Status.IN_PROGRESS; 
        }
        else if (taskStatus == Status.DONE) {
            this.taskStatus = Status.DONE;
        }
        else{
            this.taskStatus = Status.PENDING; // default value
        }    
    }
    // getters and setters
    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public Status getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(Status taskStatus) {
        this.taskStatus = taskStatus;
    }
    // toString method
    public String toString() {
        return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
                + ", taskStatus=" + taskStatus + "]";
    }

}
