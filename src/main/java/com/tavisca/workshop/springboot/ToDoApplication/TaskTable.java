package com.tavisca.workshop.springboot.ToDoApplication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaskTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String taskName;

    public TaskTable(int id, String taskName) {
        this.taskName = taskName;
        this.id = id;
    }

    public TaskTable(){

    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }
}
