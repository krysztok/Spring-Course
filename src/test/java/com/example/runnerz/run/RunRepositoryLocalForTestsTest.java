package com.example.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunRepositoryInMemoryForTestsTest {

    RunRepositoryInMemoryForTests repository;

    @BeforeEach
    void setUp() {
        repository = new RunRepositoryInMemoryForTests();
        repository.create(new Run(1, "Test Run 1", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
        repository.create(new Run(2, "Test Run 2", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
    }

    @Test
    void shouldFindAllRuns() {
        assertEquals(2, repository.findAll().size(), "Should have returned 2 runs");
    }


}