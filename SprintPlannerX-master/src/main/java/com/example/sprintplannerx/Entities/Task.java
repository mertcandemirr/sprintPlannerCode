package com.example.sprintplannerx.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue
    private Long ID;

    private String Name;

    private String Status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    private User Developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyst_id")
    private User Analyst;

    private Date DueDate;

    private Integer finalSP;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event Event;


    private boolean isStarred;

    public void setIsStarred(boolean starred) {
        isStarred = starred;
    }


}
