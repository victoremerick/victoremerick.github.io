package com.tasklist.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tasklist.dtos.TaskDto;
import com.tasklist.entities.Task;
import com.tasklist.exception.TaskServiceException;
import com.tasklist.response.Response;
import com.tasklist.services.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping
	public ResponseEntity<Response<Task>> create(@Valid @RequestBody TaskDto taskDto, BindingResult result){
		Response<Task> response = new Response<Task>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Task TaskSalva = this.taskService.salvar(taskDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskDto.getId())
				.toUri();
		response.setData(TaskSalva);
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Task>> buscar(@PathVariable("id") Long id) {
		  
		Task Task;
		try {
			Task = taskService.buscar(id);
		} catch (TaskServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		Response<Task> response = new Response<Task>();
		response.setData(Task);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<Task>> listar() {
		List<Task> viagens = taskService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(viagens);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Task>> delete(@PathVariable("id") Long id) {
		  
		Task Task;
		try {
			Task = taskService.buscar(id);
		} catch (TaskServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		Response<Task> response = new Response<Task>();
		response.setData(Task);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Task>> editar(@PathVariable("id") Long id, @Valid @RequestBody TaskDto taskDto, BindingResult result) {
		Response<Task> response = new Response<Task>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Task TaskSalva = null;
		try {
			TaskSalva = this.taskService.editar(id, taskDto);
		} catch (TaskServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskDto.getId())
				.toUri();
		response.setData(TaskSalva);
		return ResponseEntity.created(location).body(response);
	}
}
