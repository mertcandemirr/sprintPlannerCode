package com.example.sprintplannerx.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue
    private Long Id;

    private String eventName;

    private Date startDate;

    private Date endDate;


    @OneToMany
    private List<Task> tasks = new ArrayList<>();

    @OneToMany
    private List<User> users = new ArrayList<>();

    @OneToOne
    private User lead;
}
