package com.tasklist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tasklist.entities.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{

}
