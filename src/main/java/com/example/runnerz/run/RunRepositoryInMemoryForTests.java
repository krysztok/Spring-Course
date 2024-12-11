package com.example.runnerz.run;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RunRepositoryInMemoryForTests {

    private List<Run> runs = new ArrayList<>();

    public List<Run> findAll() {
        return runs;
    }

    public Optional<Run> findById(int id) {
        return runs.stream().filter(run -> run.id() == id).findFirst();
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
        runs.add(new Run(2, "Second Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.INDOOR));

    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }


}
