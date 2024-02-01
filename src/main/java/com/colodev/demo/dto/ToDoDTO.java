package com.colodev.demo.dto;

import com.colodev.demo.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoDTO {
    private Long id;
    private String name;
    private Boolean completed;

    public ToDoDTO(ToDo entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.completed = entity.getCompleted();
    }
}
