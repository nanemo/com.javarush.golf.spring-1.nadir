package com.javarush.controller;

import com.javarush.entity.Task;
import com.javarush.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String tasks(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<Task> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/{id}")
    public String edit(Model model,
                       @PathVariable(name = "id") String sID,
                       @RequestBody TaskInfo taskInfo) {
        taskService.edit(sID, taskInfo.getDescription(), taskInfo.getStatus());

        return tasks(model, 1, 10);
    }

    @PostMapping("/")
    public String add(Model model,
                    @RequestBody TaskInfo taskInfo) {
        taskService.create(taskInfo.getDescription(), taskInfo.getStatus());

        return tasks(model, 1, 10);
    }

    @DeleteMapping("/{id}")
    public String delete(Model model,
                       @PathVariable(name = "id") String sID) {
        taskService.delete(sID);

        return tasks(model, 1 , 10);
    }

    public TaskService getTaskService() {
        return taskService;
    }
}
