package com.example.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

  private List<Run> runs = new ArrayList<>();

//    public List<Run> findAll() {
//        return runs;
//    }
//
//    public Optional<Run> findById(int id) {
//        return runs.stream().filter(run -> run.id() == id).findFirst();
//    }
//
//    @PostConstruct
//    private void init() {
//        runs.add(new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
//        runs.add(new Run(2, "Second Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.INDOOR));
//
//    }
//
//    void create(Run run) {
//        runs.add(run);
//    }
//
//    void update(Run run, Integer id) {
//        Optional<Run> existingRun = findById(id);
//        if (existingRun.isPresent()) {
//            runs.set(runs.indexOf(existingRun.get()), run);
//        }
//    }
//
//    void delete(Integer id) {
//        runs.removeIf(run -> run.id().equals(id));
//    }

    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    public Optional<Run> findById(int id) {
            return jdbcClient.sql("select * from run where id = :id").param("id", id).query(Run.class).optional();
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("insert into Run(id, title, started_on, completed_on, miles, location) values (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString())).update();

        Assert.state(updated == 1, "Failed to create run " + run.title());
    }


    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("update Run set title = ?, started_on = ?, completed_on = ?, miles = ?, location =? where id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id)).update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from Run where id = :id").param("id", id).update();

        Assert.state(updated == 1, "Failed to delete run " + id);
    }


    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }
}
