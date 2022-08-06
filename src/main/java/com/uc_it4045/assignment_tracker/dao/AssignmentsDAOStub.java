package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.Assignment;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("test")
public class AssignmentsDAOStub implements IAssignmentDAO {
    Map<Integer, Assignment> allAssignments = new HashMap<>();

    @Override
    public Assignment save(Assignment assignment) throws Exception {
        allAssignments.put(assignment.getId(), assignment);
        return assignment;
    }

    @Override
    public List<Assignment> fetchAll() {
        return new ArrayList<Assignment>(allAssignments.values());
    }

    @Override
    public Assignment fetch(int id) {
        return allAssignments.get(id);
    }

    @Override
    public void delete(int id) {
        allAssignments.remove(id);
    }

    @Override
    public List<Assignment> fetchUserAssignments(int userId) throws IOException {
        return new ArrayList<Assignment>();
    }
}
