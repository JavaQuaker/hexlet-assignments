package exercise.controller;

import java.util.ArrayList;
import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping(path = "")
    public List<TaskDTO> index() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> result = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = taskMapper.map(task);
            result.add(taskDTO);
        }
        return result;
    }
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO show(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id" + " " + id + " " + "not found"));
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO create(@RequestBody TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        taskRepository.save(task);
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO update(@RequestBody @Valid TaskUpdateDTO taskData, @PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id" + " " + id + " " + "not found"));
        var user = userRepository.findById(taskData.getAssigneeId())
                        .orElseThrow(() -> new ResourceNotFoundException("not found"));

        taskMapper.update(taskData, task);
        task.setAssignee(user);
        taskRepository.save(task);
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id" + " " + id + " " + "not found"));
        taskMapper.map(task);
        taskRepository.delete(task);
    }
    // END
}
