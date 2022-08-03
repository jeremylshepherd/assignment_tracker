package com.uc_it4045.assignment_tracker.controllers;

import com.uc_it4045.assignment_tracker.Status;
import com.uc_it4045.assignment_tracker.config.JwtTokenUtil;
import com.uc_it4045.assignment_tracker.dto.Assignment;
import com.uc_it4045.assignment_tracker.service.IAssignmentService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AssignmentController {
    @Autowired
    private  final IAssignmentService assignmentService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AssignmentController(IAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/api/assignments")
    public ResponseEntity<List<Assignment>> fetchAllAssignments(@RequestHeader(value = "Authorization") String token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String username = getAuthUsername(token);
        List<Assignment> fetchedAssignments = assignmentService.fetchUserAssignments(username);
        if (fetchedAssignments.isEmpty()) {
            List<Assignment> assignments = getDemoAssignments();

            logger.info("Route: GET:/api/assignments returns " + assignments.toString());
            return new ResponseEntity<List<Assignment>>(assignments, headers, HttpStatus.OK);
        }
        return new ResponseEntity<List<Assignment>>(fetchedAssignments, headers, HttpStatus.OK);
    }

    private String getAuthUsername(String token) {
        String jwt = token.startsWith("Bearer") ? token.substring(7) : token;
        return jwtTokenUtil.getUsernameFromToken(jwt);
    }

    @NotNull
    private List<Assignment> getDemoAssignments() {
        List<Assignment> assignments = new ArrayList<Assignment>();
        Assignment asgn01 = new Assignment();
        Assignment asgn02 = new Assignment();
        Assignment asgn03 = new Assignment();
        asgn01.setTitle("Sample 01");
        asgn02.setTitle("Sample 02");
        asgn03.setTitle("Sample 03");
        asgn01.setDescription("These are example assignments\nto demonstrate the UI");
        asgn02.setDescription("The controls on these are currently disabled\nBut! once you click the + button and create an assignment");
        asgn03.setDescription("These will disappear\nThen you can interact with your newly created assignment(s).");
        asgn01.setStatus(Status.PENDING);
        asgn02.setStatus(Status.INPROGRESS);
        asgn03.setStatus(Status.COMPLETE);
        asgn01.setDate(LocalDateTime.now());
        asgn02.setDate(LocalDateTime.now());
        asgn03.setDate(LocalDateTime.now());
        assignments.add(asgn01);
        assignments.add(asgn02);
        assignments.add(asgn03);
        return assignments;
    }

    @GetMapping("/api/assignments/{id}")
    public ResponseEntity<Assignment> fetchAssignmentById(@PathVariable("id") int id) {
        Assignment assignment = assignmentService.fetchById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Route: GET:/api/assignments/{id} returns " + assignment.toString());
        return new ResponseEntity<Assignment>(assignment, headers, HttpStatus.OK);
    }

    @PostMapping(value="/api/assignments", consumes="application/json", produces="application/json")
    public ResponseEntity<?> createAssignment(@RequestBody Assignment assignment, @RequestHeader(value = "Authorization") String token) throws Exception {
        Assignment newAssignment = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String username = getAuthUsername(token);
        try {
            newAssignment = assignmentService.save(assignment, username);
            logger.info("Route: POST:/api/assignments returns " + newAssignment.toString());
            return new ResponseEntity<Assignment>(newAssignment, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unable to save assignment: " + e.getMessage(), e);
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/api/assignments", consumes="application/json", produces="application/json")
    public ResponseEntity updateAssignment(@RequestBody Assignment assignment, @RequestHeader(value = "Authorization") String token) {
        Assignment foundAssignment = assignmentService.fetchById(assignment.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String username = getAuthUsername(token);
        try {
            Assignment updatedAssignment = assignmentService.save(foundAssignment, username);
            logger.info("Route: PUT:/api/assignments returns " + updatedAssignment.toString());
            return new ResponseEntity<Assignment>(updatedAssignment, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unable to update assignment: " + e.getMessage(), e);
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/assignments/{id}")
    public ResponseEntity deleteAssignment(@PathVariable("id") int id, @RequestHeader(value = "Authorization") String token) {
        String username = getAuthUsername(token);
        try {
            assignmentService.delete(id, username);
            logger.info("Route: DELETE:/api/assignments/{id} assignment with id: " + id + " successfully deleted");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("OPERATION FAILED: Unable to delete assignment ID: " + id +", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
