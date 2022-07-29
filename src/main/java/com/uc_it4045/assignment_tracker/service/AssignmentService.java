package com.uc_it4045.assignment_tracker.service;

import com.uc_it4045.assignment_tracker.dao.IAssignmentDAO;
import com.uc_it4045.assignment_tracker.dto.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssignmentService implements IAssignmentService {

    @Autowired
    private final IAssignmentDAO assignmentDAO;

    public AssignmentService(IAssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    @Override
    @Cacheable
    public Assignment fetchById(int id) {
        return assignmentDAO.fetch(id);
    }

    @Override
    @CacheEvict(value = "assignment", key="#id")
    public void delete(int id) throws Exception {
        assignmentDAO.delete(id);
    }

    @Override
    public Assignment save(Assignment assignment) throws Exception {
        return assignmentDAO.save(assignment);
    }

    @Override
    public List<Assignment> fetchAll() {
        return assignmentDAO.fetchAll();
    }
}
