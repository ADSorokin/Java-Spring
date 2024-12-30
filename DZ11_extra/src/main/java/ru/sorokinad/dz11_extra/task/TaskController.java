package ru.sorokinad.dz11_extra.task;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;


    private final Counter addTaskCounter = Metrics.counter("add_task_count");

    @PostMapping("/add")
    public ResponseEntity<Task> CreateTask(@RequestBody Task task){

        addTaskCounter.increment();

        return new ResponseEntity<>(taskRepository.save(task), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {

        return   new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }


    @GetMapping("get/{id}")
    public ResponseEntity<Task>getTaskByID(@PathVariable Long id){
        return new ResponseEntity<>(taskRepository.findById(id).orElse(null),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateNote(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);

        return new ResponseEntity<>(taskRepository.save(task),HttpStatus.OK);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<Task> deleteNote(@PathVariable Long id){
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
