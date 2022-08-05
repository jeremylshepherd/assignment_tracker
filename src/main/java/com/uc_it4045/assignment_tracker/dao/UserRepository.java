package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AuthUser, Integer> {
    AuthUser findByUsername(String username);
}
