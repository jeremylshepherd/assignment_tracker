package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.AuthUser;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface UserRepository extends CrudRepository<AuthUser, Integer> {
    AuthUser findByUsername(String username);
}
