package com.example.test_task.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
public class SwaggerConfigTest {

    private ApplicationContext applicationContext;

    @Test
    public void testSwaggerConfig() {
        OpenAPI openAPI = applicationContext.getBean(OpenAPI.class);

        assertNotNull(openAPI);
        assertEquals("Test_Task ", openAPI.getInfo().getTitle());
        assertEquals("1.0", openAPI.getInfo().getVersion());
        assertEquals("API документация для ExampleTest", openAPI.getInfo().getDescription());
    }
}