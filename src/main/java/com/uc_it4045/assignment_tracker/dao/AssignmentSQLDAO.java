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
    AssignmentRepository AssignmentRepository;

    @Override
    public Assignment save(Assignment Assignment) throws Exception {
        return AssignmentRepository.save(Assignment);
    }

    @Override
    public List<Assignment> fetchAll() {
        List<Assignment> allAssignments = new ArrayList<>();
        Iterable<Assignment> Assignments = AssignmentRepository.findAll();
        for (Assignment Assignment : Assignments) {
            allAssignments.add(Assignment);
        }
        return allAssignments;
    }

    @Override
    public Assignment fetch(int id) {
        return  AssignmentRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        AssignmentRepository.deleteById(id);
    }

    @Override
    public List<Assignment> fetchAssignmentsByAssignmentId(int assignmentId) {
        return AssignmentRepository.findByAssignmentId(assignmentId);
    }

    @Override
    public List<Assignment> fetchAssignment() throws IOException {
        return null;
    }
}