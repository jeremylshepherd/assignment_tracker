package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
