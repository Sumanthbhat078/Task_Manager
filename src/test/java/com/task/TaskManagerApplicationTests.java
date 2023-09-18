package com.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.task.dto.TaskDto;
import com.task.entity.Task;
import com.task.repository.TaskRepository;
import com.task.service.TaskService;

@SpringBootTest(classes = {TaskManagerApplication.class })
class TaskManagerApplicationTests {

	@Autowired
	TaskService taskService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@MockBean
	TaskRepository taskRepository;
	
	@BeforeEach
	void setup()
	{
		Optional<Task> task=Optional.of(new Task(1,"project","complete the spring boot project",LocalDate.of(2023, 9, 30),"ongoing","high"));
        Mockito.when(taskRepository.findById(1)).thenReturn(task);	
	}
	
	@Test
	public void createTaskTest()
	{      
		    TaskDto newTask=new TaskDto();
		    newTask.setId(1);
		    newTask.setTitle("project");
		    newTask.setDescription("complete the spring boot project");
		    newTask.setDeadline(LocalDate.of(2023, 9, 30));
		    newTask.setStatus("ongoing");
		    newTask.setPriority("high");
	        taskService.createTask(newTask);
	        Task savedTask = taskRepository.findById(1).orElse(null);
	        assertNotNull(savedTask); 
	 
	        assertEquals(newTask.getTitle(), savedTask.getTitle());
	        assertEquals(newTask.getDescription(), savedTask.getDescription());
	        assertEquals(newTask.getDeadline(), savedTask.getDeadline());
	        assertEquals(newTask.getStatus(), savedTask.getStatus());
	        assertEquals(newTask.getPriority(), savedTask.getPriority());
	}
	
	
	@Test
	public void getTaskTest()
	{
		String title="project";
		Task task=taskRepository.findById(1).get();
		assertEquals(title,task.getTitle());
	}
	
	@Test
	public void updateTaskTest()
	{
	    
	    Task existingTask = taskRepository.findById(1).orElse(null);
	    existingTask.setId(1); // Set the correct task ID
	    existingTask.setTitle("updated project");
	    existingTask.setDescription("updated description");
	    existingTask.setDeadline(LocalDate.of(2023, 10, 1));
	    existingTask.setStatus("completed");
	    existingTask.setPriority("low");
	    
	    TaskDto updatedTask = new TaskDto();
	    updatedTask.setId(1); // Set the correct task ID
	    updatedTask.setTitle("updated project");
	    updatedTask.setDescription("updated description");
	    updatedTask.setDeadline(LocalDate.of(2023, 10, 1));
	    updatedTask.setStatus("completed");
	    updatedTask.setPriority("low");
	    
	    taskService.updateTask(updatedTask, 1);
	    assertNotNull(updatedTask);

	    assertEquals(existingTask.getTitle(), updatedTask.getTitle());
	    assertEquals(existingTask.getDescription(), updatedTask.getDescription());
	    assertEquals(existingTask.getDeadline(), updatedTask.getDeadline());
	    assertEquals(existingTask.getStatus(), updatedTask.getStatus());
	    assertEquals(existingTask.getPriority(), updatedTask.getPriority());
	}

	@Test
	public void deleteTaskTest() {
	    when(taskRepository.findById(1)).thenReturn(Optional.of(new Task(1, "project", "complete the spring boot project", LocalDate.of(2023, 9, 30), "ongoing", "high")));
	    taskService.deleteTask(1);
	    verify(taskRepository).delete(Mockito.any(Task.class));
	}
	

}
