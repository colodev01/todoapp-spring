package com.colodev.demo.service;

import com.colodev.demo.dto.CreateToDoDTO;
import com.colodev.demo.dto.ToDoDTO;
import com.colodev.demo.dto.UpdateToDoDTO;
import com.colodev.demo.exception.ToDoException;
import com.colodev.demo.model.ToDo;
import com.colodev.demo.repository.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    public ToDoService (ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDoDTO createTodo(CreateToDoDTO createToDoDTO) {
        ToDo newToDo = new ToDo();
        newToDo.setName(createToDoDTO.getName());
        newToDo.setCompleted(createToDoDTO.getCompleted());
        ToDo toDo = toDoRepository.save(newToDo);
        return new ToDoDTO(toDo);
    }

    public List<ToDoDTO> getToDos() {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream().map(entity -> new ToDoDTO(entity)).toList();
    }

    public List<ToDoDTO> getToDos(Boolean completed) {
        List<ToDo> toDos = toDoRepository.findByCompleted(completed);
        return toDos.stream().map(entity -> new ToDoDTO(entity)).toList();
    }

    public ToDoDTO getToDoById(Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            return new ToDoDTO(toDo.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "getToDoById - to do not found");
        }
    }

    public ToDoDTO updateToDo(Long id, UpdateToDoDTO updateToDoDTO) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            toDo.get().setName(updateToDoDTO.getName());
            toDo.get().setCompleted(updateToDoDTO.getCompleted());
            toDoRepository.save(toDo.get());
            return new ToDoDTO(toDo.get());
        } else {
            throw new ToDoException(404, "updateToDo - to do not found");
        }
    }

    public void deleteToDo(Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            toDoRepository.delete(toDo.get());
        } else {
            throw new RuntimeException("deleteToDo - to do not found");
        }
    }
}
