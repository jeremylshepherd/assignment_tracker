package com.uc_it4045.assignment_tracker.dto;

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
    private String title;
    private Status status;
    private LocalDateTime date;
}

