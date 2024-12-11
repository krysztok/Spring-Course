package com.example.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RunControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RunRepository repository;

    private final List<Run> runs = new ArrayList<>();

    @BeforeEach
    void setUp() {
        runs.add(new Run(1, "Test Run 1", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
    }

    @Test
    void shouldFindAllRuns() throws Exception {
        when(repository.findAll()).thenReturn(runs);
        mvc.perform(MockMvcRequestBuilders.get("/api/runs"))
                .andExpect(status().isOk());
    }

}