package com.uc_it4045.assignment_tracker.service;

import com.uc_it4045.assignment_tracker.dto.Assignment;
import com.uc_it4045.assignment_tracker.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface IAssignmentService {
    Assignment fetchById(int id);

    void delete(int id, String username) throws Exception;

    Assignment save(Assignment assignment, String username) throws Exception;

    List<Assignment> fetchUserAssignments(String username) throws IOException;

    List<Assignment> fetchAll();
}