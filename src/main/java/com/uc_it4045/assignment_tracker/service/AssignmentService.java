package com.uc_it4045.assignment_tracker.service;

import com.uc_it4045.assignment_tracker.dao.IAssignmentDAO;
import com.uc_it4045.assignment_tracker.dao.UserRepository;
import com.uc_it4045.assignment_tracker.dto.Assignment;
import com.uc_it4045.assignment_tracker.dto.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class AssignmentService implements IAssignmentService {

    @Autowired
    private final IAssignmentDAO assignmentDAO;
    @Autowired
    private final UserRepository userRepository;

    public AssignmentService(IAssignmentDAO assignmentDAO, UserRepository userRepository) {
        this.assignmentDAO = assignmentDAO;
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = "assignment", key="#id")
    public Assignment fetchById(int id) {
        return assignmentDAO.fetch(id);
    }

    @Override
    @CacheEvict(value = "assignment", key="#id")
    public void delete(int id, String username) throws Exception {
        AuthUser authUser = userRepository.findByUsername(username);
        Assignment assignment = assignmentDAO.fetch(id);
        if (!assignment.getUser().equals(authUser)) {
            throw new Exception();
        }
        assignmentDAO.delete(id);
    }

    @Override
    public Assignment save(Assignment assignment, String username) throws Exception {
        AuthUser authUser = userRepository.findByUsername(username);
        assignment.setUser(authUser);
        return assignmentDAO.save(assignment);
    }

    @Override
    public List<Assignment> fetchUserAssignments(String username) throws IOException {
        AuthUser authUser = userRepository.findByUsername(username);
        return assignmentDAO.fetchUserAssignments(authUser.getId());
    }

    @Override
    public List<Assignment> fetchAll() {
        return assignmentDAO.fetchAll();
    }
}
