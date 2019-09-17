package com.tavisca.workshop.springboot.ToDoApplication;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<TaskTable, Integer> {

}
