package com.uc_it4045.assignment_tracker.service;

import com.uc_it4045.assignment_tracker.dto.Assignment;

import java.util.List;

public interface IAssignmentService {
    Assignment fetchById(int id);

    void delete(int id) throws Exception;

    Assignment save(Assignment assignment) throws Exception;

    List<Assignment> fetchAll();
}