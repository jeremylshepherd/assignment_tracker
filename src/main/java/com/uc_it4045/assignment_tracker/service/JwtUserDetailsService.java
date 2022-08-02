package com.uc_it4045.assignment_tracker.service;

//import com.uc_it4045.assignment_tracker.dto.User;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("jeremylshepherd@gmail.com".equals(username)) {
            return new User(username, "$2a$10$a6C6SKJmiUMFvxy..zXQUuuUwM/wZLTm4aomKJki0PaQIPl.F0rTW", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("user not found with username: " + username);
        }
    }
}
