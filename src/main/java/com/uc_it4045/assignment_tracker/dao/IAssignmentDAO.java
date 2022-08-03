package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.Assignment;

import java.io.IOException;
import java.util.List;

public interface IAssignmentDAO {
    Assignment save(Assignment Assignment) throws Exception;

    List<Assignment> fetchAll();

    Assignment fetch(int id);

    void delete(int id);

    List<Assignment> fetchUserAssignments(int userId) throws IOException;
}
