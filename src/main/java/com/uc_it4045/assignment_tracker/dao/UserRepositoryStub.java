package com.uc_it4045.assignment_tracker.dao;

import com.uc_it4045.assignment_tracker.dto.AuthUser;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("test")
public class UserRepositoryStub implements UserRepository{
    Map<Integer, AuthUser> allAuthUsers = new HashMap<Integer, AuthUser>();
    @Override
    public AuthUser findByUsername(String username) {
        AuthUser user = new AuthUser();
        user.setUsername(username);
        return user;
    }

    @Override
    public AuthUser save(AuthUser authUser) {
        return allAuthUsers.put(authUser.getId(), authUser);
    }


    // Unused Method
    @Override
    public <S extends AuthUser> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    // Unused Method
    @Override
    public Optional<AuthUser> findById(Integer integer) {
        return Optional.empty();
    }

    // Unused Method
    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    // Unused Method
    @Override
    public Iterable<AuthUser> findAll() {
        return null;
    }

    // Unused Method
    @Override
    public Iterable<AuthUser> findAllById(Iterable<Integer> integers) {
        return null;
    }

    // Unused Method
    @Override
    public long count() {
        return 0;
    }

    // Unused Method
    @Override
    public void deleteById(Integer integer) {

    }

    // Unused Method
    @Override
    public void delete(AuthUser entity) {

    }

    // Unused Method
    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    // Unused Method
    @Override
    public void deleteAll(Iterable<? extends AuthUser> entities) {

    }

    // Unused Method
    @Override
    public void deleteAll() {

    }
}
