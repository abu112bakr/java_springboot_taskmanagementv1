package com.example.taskmanagement.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.example.taskmanagement.model.Status;

@Component // Indicate that this is a Spring-managed component
@Entity // since we want to create a table in the database using this class
public class Task {
    @Id //int taskId is the primary key
    private int taskId;
    private String taskName;
    private String taskDescription;
    //private String taskStatus; // should be enum, PENDING, IN_PROGRESS, DONE
    private String taskStatus;
    // REQUIRED BY JPA
    public Task(){

    }
    //is this constructor necessary?
    public Task(int taskId, String taskName, String taskDescription, String taskStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
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
    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    // toString method
    public String toString() {
        return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
                + ", taskStatus=" + taskStatus + "]";
    }

}
