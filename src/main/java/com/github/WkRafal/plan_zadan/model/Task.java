package com.github.WkRafal.plan_zadan.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Task's description must not be empty")
    private String description;
    private boolean done;
    private LocalDateTime deadline;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public Task() {
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadLine(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void updateFrom(final Task task) {
        description = task.description;
        done = task.done;
        deadline = task.deadline;
    }

    @PrePersist  //before persist to base
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preMarge() {
        updatedOn = LocalDateTime.now();
    }

}
