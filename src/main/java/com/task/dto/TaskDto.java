package com.task.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskDto {
	    private int id;
	    @NotBlank(message="title cannot be blank")
	    @Size(max=15,message="title can't be of more than 15 characters")
	    private String title;
	    private String description;
	    @Future(message="Deadline must be a future date")
	    private LocalDate deadline;
	    private String status;
	    private String priority;
	    
		public TaskDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public TaskDto(int id, String title, String description, LocalDate deadline, String status, String priority) {
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
			return "TaskDto [id=" + id + ", title=" + title + ", description=" + description + ", deadline=" + deadline
					+ ", status=" + status + ", priority=" + priority + "]";
		}
	    
}
