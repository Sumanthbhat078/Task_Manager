package com.task.service;

import java.util.List;

import org.springframework.ui.Model;

import com.task.dto.TaskDto;

public interface TaskService {
    void createTask(TaskDto task);
    List<TaskDto> getAllTasks();
    void updateTask(TaskDto task,int taskId);
    void deleteTask(int taskId);
	TaskDto getTaskById(int taskId, Model model);
}
