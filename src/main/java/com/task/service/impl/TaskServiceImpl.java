package com.task.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.task.dto.TaskDto;
import com.task.entity.Task;
import com.task.exception.ResourceNotFoundException;
import com.task.repository.TaskRepository;
import com.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
    
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public void createTask(TaskDto taskDto) {
		Task task=modelMapper.map(taskDto,Task.class);
		taskRepo.save(task);
	}

	@Override
	public TaskDto getTaskById(int taskId, Model model) {
		return null;
	}
	

	@Override
	public List<TaskDto> getAllTasks() {
		List<Task> tasks=taskRepo.findAll();
		List<TaskDto> taskDtos = tasks.stream().map((task) -> this.modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
		return taskDtos;
	}

	@Override
	public void updateTask(TaskDto updatedTask, int taskId) {
		Optional<Task> task=taskRepo.findById(taskId);
		TaskDto existingTask=modelMapper.map(task, TaskDto.class);
		existingTask.setTitle(updatedTask.getTitle());
		existingTask.setDescription(updatedTask.getDescription());
		existingTask.setStatus(updatedTask.getStatus());
		existingTask.setDeadline(updatedTask.getDeadline());
		existingTask.setPriority(updatedTask.getPriority());
		taskRepo.save(modelMapper.map(existingTask, Task.class));
		
	}

	@Override
	public void deleteTask(int taskId) {
		Task task=taskRepo.findById(taskId).orElseThrow(()->new ResourceNotFoundException(taskId));
		taskRepo.delete(task);
	}



}
	

