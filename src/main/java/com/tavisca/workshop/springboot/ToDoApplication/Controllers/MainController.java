package com.tavisca.workshop.springboot.ToDoApplication.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {
    private List<String> tasks = new ArrayList<String>() {
        {
            add("Java");
            add("Scala");
            add("Python");
        }
    };

    @GetMapping(path = "/api/v1/getAllTasks")
    public ResponseEntity<?> getAllTasks() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taskList", tasks);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/v1/getTaskByID/{id}")
	public ResponseEntity<?> getTaskByID(@PathVariable("id") int index){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskList", tasks.get(index));
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/v1/searchTask/{taskName}")
	public ResponseEntity<?> search(@PathVariable("taskName") String name)
	{
		JSONObject jsonObject = new JSONObject();
		if(tasks.contains(name))
		{
			jsonObject.put("taskList",name);
			return new ResponseEntity<>(jsonObject.toString(),HttpStatus.OK);
		}
		jsonObject.put("taskList","No entries found!!");
		return new ResponseEntity<>(jsonObject.toString(),HttpStatus.OK);
	}

    @PostMapping(path = "/api/v1/addTask")
	public ResponseEntity<?> addTask(@RequestBody String entry)
	{
		JSONObject jsonObject = new JSONObject(entry);
		tasks.add(jsonObject.getString("taskList"));
		return getAllTasks();
	}

    @PutMapping(path = "/api/v1/editTask/{id}")
	public ResponseEntity<?> editTask(@RequestBody String name,@PathVariable("id") int index)
	{
		JSONObject jsonObject = new JSONObject(name);
		tasks.set(index,jsonObject.getString("taskList"));
		return getAllTasks();
	}

    @DeleteMapping(path = "/api/v1/deleteTask/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable("id") int index)
	{
		tasks.remove(index);
		return getAllTasks();
	}
}