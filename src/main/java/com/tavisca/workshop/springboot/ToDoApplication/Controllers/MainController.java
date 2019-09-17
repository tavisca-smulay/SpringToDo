package com.tavisca.workshop.springboot.ToDoApplication.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.tavisca.workshop.springboot.ToDoApplication.DataPersistService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {
	@Autowired
	DataPersistService dataPersistService;

    private List<String> tasks = new ArrayList<String>() {
        {
            add("Java");
            add("Scala");
            add("Python");
        }
    };

    @GetMapping(path = "/api/v1/getAllTasks")
    public ResponseEntity<?> getAllTasks() {
    	List<String> list = dataPersistService.getAllTasks();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taskList", list);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/v1/getTaskByID/{id}")
	public ResponseEntity<?> getTaskByID(@PathVariable("id") int index){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskList", tasks.get(index));
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/v1/searchTask/{taskName}")
	public ResponseEntity<?> searchTask(@PathVariable("taskName") String taskName)
	{
		List<String> list = dataPersistService.getAllTasks();
		JSONObject jsonObject = new JSONObject();
		if(list.contains(taskName))
		{
			jsonObject.put("taskList",taskName);
			return new ResponseEntity<>(jsonObject.toString(),HttpStatus.OK);
		}
		jsonObject.put("taskList","No entries found!!");
		return new ResponseEntity<>(jsonObject.toString(),HttpStatus.OK);
	}

    @PostMapping(path = "/api/v1/addTask")
	public ResponseEntity<?> addTask(@RequestBody String entry)
	{
		JSONObject jsonObject = new JSONObject(entry);
		dataPersistService.addEntry(jsonObject.getString("taskList"));
		return getAllTasks();
	}

    @PutMapping(path = "/api/v1/editTask/{data}")
	public ResponseEntity<?> editTask(@RequestBody String oldTask,@PathVariable("data") String newTask)
	{
		JSONObject jsonObject = new JSONObject(oldTask);
		dataPersistService.updateEntry(jsonObject.getString("taskList"),newTask);
		return getAllTasks();
	}

    @DeleteMapping(path = "/api/v1/deleteTask/{data}")
	public ResponseEntity<?> deleteTask(@PathVariable("data") String data)
	{
		dataPersistService.deleteEntry(data);
		return getAllTasks();
	}
}