package com.task.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private String status;
    private String priority;
    
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Task(int id, String title, String description, LocalDate deadline, String status, String priority) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.status = status;
		this.priority = priority;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", deadline=" + deadline
				+ ", status=" + status + ", priority=" + priority + "]";
	}
    
}
