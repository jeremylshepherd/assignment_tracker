package com.uc_it4045.assignment_tracker.dto;

import com.uc_it4045.assignment_tracker.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "assignment")
    private List<Assignment> assignments;
}

