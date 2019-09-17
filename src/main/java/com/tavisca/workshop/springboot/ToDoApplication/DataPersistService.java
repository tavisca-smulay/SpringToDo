package com.tavisca.workshop.springboot.ToDoApplication;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DataPersistService {
    @Autowired
TaskRepo taskRepo;

    public List<String> getAllTasks(){
        List<String> list = new ArrayList<>();
        taskRepo.findAll().forEach(e->{
            list.add(e.getTaskName());
        });
        return list;
    }

public List<String> addEntry(String taskName){
    TaskTable taskTable = new TaskTable();
    taskTable.setTaskName(taskName);
    taskRepo.save(taskTable);
    return getAllTasks();
}

public void deleteEntry(String taskName){
        TaskTable taskTable = new TaskTable();
        taskTable.setTaskName(taskName);
        List<String> list = getAllTasks();
        for(String s : list){
            if(s.equals(taskName)){
                taskRepo.delete(taskTable);
            }
        }
}

public void updateEntry(String oldTask, String newTask){
        List<String> list = getAllTasks();
        TaskTable taskTable = new TaskTable();
        for(String s : list){
            if(s.equals(oldTask)){
                taskRepo.delete(taskTable);
                taskTable.setTaskName(newTask);
                taskRepo.save(taskTable);
            }
        }
}
}
