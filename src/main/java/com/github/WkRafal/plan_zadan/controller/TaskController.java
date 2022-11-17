package com.github.WkRafal.plan_zadan.controller;

import com.github.WkRafal.plan_zadan.model.Task;
import com.github.WkRafal.plan_zadan.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class TaskController {
    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    TaskController(final TaskRepository repository) {
        this.repository = repository;
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/tasks") //to samo poniżej
    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> readAllTasks() {
        logger.warn("Exposing all the tasks:");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PostMapping("/tasks")
    ResponseEntity<Task> created(@RequestBody @Valid Task task) {
        Task result = repository.save(task);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> findById(@PathVariable int id) {
        return repository.findById(id)
                .map(task -> ResponseEntity.ok(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/tasks/{id}")
//    ResponseEntity<?> updateTask(@PathVariable("id") int taskId, @RequestBody @Valid Task update) {
    ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody @Valid Task update) {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        update.setId(id);
        repository.save(update);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> toggleTask(@PathVariable int id) {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        repository.findById(id)
                .ifPresent(task -> task.setDone(!task.isDone()));
        return ResponseEntity.noContent().build();
    }
}
