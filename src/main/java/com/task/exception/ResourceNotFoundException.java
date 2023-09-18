package com.task.exception;

public class ResourceNotFoundException extends RuntimeException{
  private int id;
  public ResourceNotFoundException(int id)
  {
	  super(String.format("Task with id: %s not found!!",id));
	  this.id=id;
  }
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
