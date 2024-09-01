package com.example.test_task.exception.handler;

import com.example.test_task.exception.BadRequestException;
import com.example.test_task.exception.ExceptionResponse;
import com.example.test_task.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testNotFoundException() throws Exception {
        mockMvc.perform(get("/api/some-endpoint-that-throws-notfoundexception"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Task not found"))
                .andExpect(jsonPath("$.exceptionClassName").value("NotFoundException"))
                .andExpect(jsonPath("$.httpStatus").value(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testBadRequestException() throws Exception {
        mockMvc.perform(get("/api/some-endpoint-that-throws-badrequestexception"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Invalid request"))
                .andExpect(jsonPath("$.exceptionClassName").value("BadRequestException"))
                .andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value()));
    }
}
