package com.tasklist.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.tasklist.dtos.TaskDto;
import com.tasklist.entities.Task;
import com.tasklist.exception.TaskServiceException;
import com.tasklist.repository.TaskRepository;

@Service
@EnableJpaRepositories("com.michaelcgood.dao")
@EntityScan("com.michaelcgood.model")
public class TaskService {

	@Autowired
	private TaskRepository TaskRepository;

	public List<Task> listar() {
		List<Task> tasks = new ArrayList<Task>();
		TaskRepository.findAll().forEach(tasks::add);
		return tasks;
	}

	public Task salvar(TaskDto TaskDto) {
		Task Task = new Task();

		Task.setTitulo(TaskDto.getTitulo());
		Task.setDescricao(TaskDto.getDescricao());
		Task.setStatus(1L);
		Task.setData_criacao(new Date());
		TaskRepository.save(Task);
		return Task;
	}

	public Task buscar(Long id) throws TaskServiceException {
		Task Task = TaskRepository.findOne(id);

		if (Task == null) {
			throw new TaskServiceException("Não existe esta Task cadastrada");
		}
		return Task;
	}
	
	public Task deletar(Long id) throws TaskServiceException {
		Task task = TaskRepository.findOne(id);
		
		if (task == null) {
			throw new TaskServiceException("Não existe esta Task cadastrada");
		}
		
		task.setData_remocao(new Date());
		TaskRepository.save(task);
		return task;
	}

	public Task editar(Long id, TaskDto taskDto) throws TaskServiceException {
		Task task = TaskRepository.findOne(id);
		
		if (task == null) {
			throw new TaskServiceException("Não existe esta Task cadastrada");
		}
		
		Date date = new Date();
		
		task.setDescricao(taskDto.getDescricao());
		task.setStatus(taskDto.getStatus());
		task.setData_edicao(date);
		if(taskDto.getStatus()==0) {
			task.setData_conclusao(date);
		}
		TaskRepository.save(task);
		return task;
	}
}