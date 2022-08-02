package com.uc_it4045.assignment_tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    // TODO: Email validation
    @Column(unique = true)
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
}
