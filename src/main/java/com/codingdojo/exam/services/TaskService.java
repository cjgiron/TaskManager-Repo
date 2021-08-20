package com.codingdojo.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.exam.models.Task;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.repositories.TaskRepository;

@Service
public class TaskService {
	
private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	
	public Task createTask(Task t) {
		return taskRepository.save(t);
	}
	
	public List<Task> allTasks() {
		return taskRepository.findAll();
	}
	
	public Task findTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            return null;
        }
    }
	
	public Task updateTask(Long id, String content, String priority, User creator, User assignee) {
		Task t = this.findTask(id);
		
		t.setId(id);
		t.setContent(content);
		t.setPriority(priority);
		t.setCreator(creator);
		t.setAssignee(assignee);
		
		return taskRepository.save(t);
	}


}
