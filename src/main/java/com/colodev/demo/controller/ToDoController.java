package com.colodev.demo.controller;

import com.colodev.demo.dto.CreateToDoDTO;
import com.colodev.demo.dto.ToDoDTO;
import com.colodev.demo.dto.UpdateToDoDTO;
import com.colodev.demo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController (ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("")
    public ResponseEntity<ToDoDTO> createToDo(@RequestBody CreateToDoDTO newToDo) {
        ToDoDTO toDoDTO = toDoService.createTodo(newToDo);
        return new ResponseEntity<>(toDoDTO, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<ToDoDTO> getToDos(@RequestParam Optional<Boolean> completed) {
        if (completed.isPresent()) {
            return toDoService.getToDos(completed.get());
        }
        return toDoService.getToDos();
    }

    @GetMapping("/{id}")
    public ToDoDTO getToDoById(@PathVariable Long id) {
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDoDTO updateToDo(@PathVariable Long id, @RequestBody UpdateToDoDTO updateToDoDTO) {
        return toDoService.updateToDo(id, updateToDoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}