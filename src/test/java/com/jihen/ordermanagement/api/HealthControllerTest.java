package com.jihen.ordermanagement.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthController.class)
/**
 * HTTP-layer tests for the application's health endpoint.
 */
class HealthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    /**
     * Verifies that the health endpoint returns HTTP 200 and an UP status.
     *
     * @throws Exception if the MockMvc request cannot be performed
     */
    void healthReturnsUp() throws Exception {
        mockMvc.perform(get("/api/v1/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
