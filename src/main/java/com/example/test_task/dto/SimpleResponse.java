package com.example.test_task.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleResponse {
    private HttpStatus status;
    private String message;

    public SimpleResponse(String message) {
        this.message = message;
    }
}
