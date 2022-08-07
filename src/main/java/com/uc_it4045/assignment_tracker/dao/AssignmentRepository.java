package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.Assignment;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Profile("!test")
public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
    List<Assignment> findByUserId(int userId);
}
