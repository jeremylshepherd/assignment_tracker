package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile({"dev", "default"})
public class AssignmentSQLDAO implements IAssignmentDAO {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Override
    public Assignment save(Assignment assignment) throws Exception {
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> fetchAll() {
        List<Assignment> allAssignments = new ArrayList<>();
        Iterable<Assignment> Assignments = assignmentRepository.findAll();
        for (Assignment Assignment : Assignments) {
            allAssignments.add(Assignment);
        }
        return allAssignments;
    }

    @Override
    public Assignment fetch(int id) {
        return  assignmentRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public List<Assignment> fetchUserAssignments(int userId) throws IOException {
        return assignmentRepository.findByUserId(userId);
    }
}