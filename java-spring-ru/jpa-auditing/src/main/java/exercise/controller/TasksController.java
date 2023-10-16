package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "")
    public List<Task> index() {
        return taskRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Task show(@PathVariable long id) {

        var task =  taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        return task;
    }

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    Task create(@RequestBody Task task) {
       if (taskRepository.findAll().contains(task)) {
           throw new RuntimeException("Task is already exist");
       } else {
           taskRepository.save(task);
       }

       return task;
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@PathVariable long id, @RequestBody Task data) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id" + " " + id + " " + "not found"));
        task.setTitle(data.getTitle());
        task.setDescription(data.getDescription());
        task.setCreatedAt(data.getCreatedAt());
        return task;

    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
}
