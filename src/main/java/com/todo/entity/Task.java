package com.todo.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "task", schema = "todo")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Status status;
    private BigInteger time;

    public Task(String description, Status status, BigInteger time) {
        this.description = description;
        this.status = status;
        this.time = time;
    }

    public Task() {

    }

}
