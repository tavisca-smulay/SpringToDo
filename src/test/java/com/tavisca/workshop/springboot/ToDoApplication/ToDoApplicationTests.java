package com.tavisca.workshop.springboot.ToDoApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ToDoApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void contextLoads() throws Exception {
		MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllTasks")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.taskList",hasSize(3)))
				.andDo(print()).andReturn().getResponse();
		assertEquals(200,mockHttpServletResponse.getStatus());
}

	@Test
	public void getTaskByIDTest() throws Exception{
		MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/getTaskByID/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.taskList").value("Scala")).andDo(print()).andReturn().getResponse();
		assertEquals(200,mockHttpServletResponse.getStatus());
	}

	@Test
	public void addTaskTest() throws Exception {
		String bodyContent = "{\"taskList\":\"Android\"}";
		RequestBuilder requestBuilder =   MockMvcRequestBuilders
				.post("/api/v1/addTask")
				.accept(MediaType.APPLICATION_JSON)
				.content(bodyContent)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(jsonPath("$.taskList",hasSize(4))).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(200, response.getStatus());
	}

	@Test
	public void deleteTaskTest() throws Exception{

	}

	@Test
	public void updateTaskTest() throws Exception{

	}

	@Test
	public void searchTaskTest() throws Exception{

	}
}
