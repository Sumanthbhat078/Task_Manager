package com.task.controller;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.task.dto.TaskDto;
import com.task.entity.Task;
import com.task.exception.ResourceNotFoundException;
import com.task.repository.TaskRepository;
import com.task.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;



@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@GetMapping("/home")
	@Operation(summary = "home page: Start here")
	public String Home()
	{
		return "home";
	}
	
	@GetMapping("/createTaskForm")
	@Operation(summary = "form to create a task")
	public String createTaskForm()
	{
		return "createTaskForm";
	}
	
	@PostMapping("/createTask")
	@Operation(summary = "create/add a task")
	public String createTask(@Valid @ModelAttribute TaskDto taskDto, BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("validationErrors", result.getAllErrors());
		}
		else
		{
		taskService.createTask(taskDto);
		model.addAttribute("message", "Task successfully created");
		}
		return "createTaskForm";
	}
	
	@PostMapping("/getTask")
	@Operation(summary = "get task details by Id")
	public String getTaskById(@RequestParam("id") int taskId,Model model)
	{
		
		try {
			Task task=taskRepo.findById(taskId).orElseThrow(()->new ResourceNotFoundException(taskId));
			TaskDto taskDto=modelMapper.map(task, TaskDto.class);
			model.addAttribute("task",taskDto);
			}
			catch(ResourceNotFoundException ex)
			{
				model.addAttribute("NotFound","Task not found");
				return "home";
			}
		
		return "show_task";
	}
	
	@GetMapping("/getAllTasks")
	@Operation(summary = "get list of all tasks")
	public String getAllTasks(Model model)
	{
		List<TaskDto> allTasks=taskService.getAllTasks();
		model.addAttribute("AllTasks",allTasks);
		return "ShowAllTasks";
	}
	
	@GetMapping("/showUpdateForm/{taskId}")
	@Operation(summary = "Form to update task details")
	public String updateForm(@PathVariable int taskId,Model model)
	{
		Optional<Task> task=taskRepo.findById(taskId);
		model.addAttribute("task", task.orElse(new Task()));
		return "updateForm";
	}
	
	@PostMapping("/updateTask")
	@Operation(summary = "update a task")
	public String updateTask(@Valid @ModelAttribute("task") TaskDto updatedTaskDto,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("validationErrors",result.getAllErrors());
		}
		else
		{
			int taskId=updatedTaskDto.getId();
			taskService.updateTask(updatedTaskDto,taskId);
			model.addAttribute("message", "Task successfully updated");
		}		
		return "updateForm";
	}
	
	@GetMapping("/deleteTask/{taskId}")
	@Operation(summary = "delete a task by Id")
	public String deleteTask(@PathVariable("taskId") int taskId,Model model)
	{
		taskService.deleteTask(taskId);
		model.addAttribute("message", "Task successfully deleted");
		return "home";
	}
	
}
