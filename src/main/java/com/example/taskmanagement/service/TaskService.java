package com.example.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.repository.TaskRepo;
import com.example.taskmanagement.model.Task;

import java.util.List;

@Service // Indicate that this is a Spring-managed service component
public class TaskService {
    @Autowired
    TaskRepo taskRepo; // dependency injection of TaskRepo(repository)
    // Task.java have the following  methods
    // getTaskId, setTaskId,getTaskName,setTaskName,getTaskDescription,SetTaskDescription,GetTaskStatus,setTaskStatus,toString

    public List<Task> getTasks() {
        return taskRepo.findAll();
    }
    public Task getTaskById(int taskId){
        return taskRepo.findById(taskId).orElse(new Task( 0, "Not Found", "Not Found", "Not Found"));
  
    }
    public Task addTask(Task task){
        return taskRepo.save(task);
    }
    public void updateTask(Task task){
        taskRepo.save(task);
        System.out.println(task + " updated successfully");
    }
    public void deleteTask(int taskId){
        taskRepo.deleteById(taskId);
        System.out.println("Task with id " + taskId + " deleted successfully");
    }




}
