package com.uc_it4045.assignment_tracker.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uc_it4045.assignment_tracker.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public @Data
class Assignment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private Status status;
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AuthUser user;
}

