package com.uc_it4045.assignment_tracker.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


//@Entity
//@Data
public class User {
    private String email;
    private String firstName;
    private String lastName;
}
