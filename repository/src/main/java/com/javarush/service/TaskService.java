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

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll(int offset, int limit) {
        return getTaskRepository().getAll(offset, limit);
    }

    public int getAllCount() {
        return getTaskRepository().getAllCount();
    }

    @Transactional
    public Task edit(String sId, String description, Status status) {
        Long id = catchException(sId);
        if (!Objects.isNull(getTaskRepository().getById(id))) {
            getTaskRepository().saveOrUpdate(new Task(Integer.parseInt(sId), description, status));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REQUEST_MESSAGE + id);
        }
    }

    public Task create(String description, Status status) {

    }

    @Transactional
    public void delete(int id) {

    }

    private Long catchException(String sID) {
        try {
            long id = Long.parseLong(sID);
            if (id < 0 || id == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + sID);
            }
            return id;
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REQUEST_MESSAGE + sID);
        }
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

}
