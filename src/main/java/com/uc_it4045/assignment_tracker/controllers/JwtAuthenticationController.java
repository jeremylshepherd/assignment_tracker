package com.uc_it4045.assignment_tracker.controllers;

import com.uc_it4045.assignment_tracker.config.JwtTokenUtil;
import com.uc_it4045.assignment_tracker.dto.JwtRequest;
import com.uc_it4045.assignment_tracker.dto.JwtResponse;
import com.uc_it4045.assignment_tracker.dto.UserDTO;
import com.uc_it4045.assignment_tracker.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken (@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(jwtUserDetailsService.save(userDTO));
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> sendUser(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(jwtUserDetailsService.getUser(userDTO));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User Disabled: ", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials: ", e);
        }

    }
}
