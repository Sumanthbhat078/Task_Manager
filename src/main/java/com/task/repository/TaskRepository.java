package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{

	/*
	 if we want to use a custom finder method, we can write a query as follows, 
	 but here i have not used it due to limiited functionality.
	          
	          @Query("select t from Task t where t.id=:id")
	          Task getTaskById(@Param("id") int taskId); 
	 */
	
	
}
