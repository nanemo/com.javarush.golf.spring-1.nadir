package com.javarush.service;

import com.javarush.entity.Status;
import com.javarush.entity.Task;
import com.javarush.exception.GlobalExceptionHandler;
import com.javarush.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    private TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll(int offset, int limit) {
        return getTaskRepository().getAll(offset, limit);
    }

    public int getAllCount() {
        return taskRepository.getAllCount();
    }

    @Transactional
    public Task edit(String sID, String description, Status status) {
        int id = GlobalExceptionHandler.catchException(sID);
        Task task = new Task();
        if (Objects.isNull(getTaskRepository().getById(id))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, GlobalExceptionHandler.NOT_FOUND_REQUEST_MESSAGE + id);
        }
        task.setDescription(description);
        task.setStatus(status);
        taskRepository.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskRepository.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public void delete(String sID) {
        int id = GlobalExceptionHandler.catchException(sID);
        Task task = getTaskRepository().getById(id);
        if (Objects.isNull(task)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, GlobalExceptionHandler.NOT_FOUND_REQUEST_MESSAGE + id);
        }
        getTaskRepository().delete(task);
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

}
