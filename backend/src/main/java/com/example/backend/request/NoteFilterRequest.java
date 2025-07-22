package com.example.backend.request;

import lombok.Data;

@Data
public class NoteFilterRequest {
    private Boolean indicator;
    private Integer quantity;
}
