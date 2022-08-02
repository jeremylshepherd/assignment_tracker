package com.uc_it4045.assignment_tracker.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


@Entity
public @Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String email;
    private String firstName;
    private String lastName;
}
