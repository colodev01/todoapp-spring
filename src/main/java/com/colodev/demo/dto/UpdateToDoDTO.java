package com.colodev.demo.dto;

import lombok.Data;

@Data
public class UpdateToDoDTO {
    private String name;
    private Boolean completed;
}
