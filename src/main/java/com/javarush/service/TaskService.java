package com.javarush.service;

import com.javarush.entity.Status;
import com.javarush.entity.Task;
import com.javarush.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private static final String BAD_REQUEST_MESSAGE = "Given ID is invalid: ";
    private static final String NOT_FOUND_REQUEST_MESSAGE = "ID is not found: ";
    private static final String NUMBER_FORMAT_EXCEPTION = "Please enter number: ";

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll(int offset, int limit) {
        return getTaskRepository().getAll(offset, limit);
    }

    public int getAllCount() {
        return taskRepository.getAllCount();
    }

    @Transactional
    public Task edit(int id, String description, Status status) {
//        int id = catchException(sId);
        Task task = new Task();
        if (Objects.isNull(getTaskRepository().getById(id))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REQUEST_MESSAGE + id);
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
    public void delete(int id) {
//        int i = catchException(sID);
        Task task = getTaskRepository().getById(id);
        if (Objects.isNull(task)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REQUEST_MESSAGE + id);
        }
        getTaskRepository().delete(task);
    }

    private int catchException(String sID) {
        try {
            int id = Integer.parseInt(sID);
            if (id < 0 || id == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + sID);
            }
            return id;
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, NUMBER_FORMAT_EXCEPTION + sID);
        }
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

}
