package com.codingdojo.exam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.exam.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
	
	List<Task> findAll();
	

}
