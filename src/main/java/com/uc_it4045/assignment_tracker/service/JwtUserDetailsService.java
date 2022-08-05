package com.uc_it4045.assignment_tracker.service;

//import com.uc_it4045.assignment_tracker.dto.User;
import com.uc_it4045.assignment_tracker.dao.UserRepository;
import com.uc_it4045.assignment_tracker.dto.AuthUser;
import com.uc_it4045.assignment_tracker.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = userRepository.findByUsername(username);
        if (authUser == null) {
            throw new UsernameNotFoundException("user not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(authUser.getUsername(), authUser.getPassword(), new ArrayList<>());
    }

    public AuthUser save(UserDTO user) {
        AuthUser newUser = new AuthUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        return userRepository.save(newUser);
    }
    public AuthUser getUser(UserDTO user) {
        return userRepository.findByUsername(user.getUsername());
    }

    public AuthUser getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
