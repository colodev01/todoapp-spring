package com.colodev.demo.dto;

import lombok.Data;

@Data
public class CreateToDoDTO {
    private String name;
    private Boolean completed;
}
