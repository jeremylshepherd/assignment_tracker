package com.uc_it4045.assignment_tracker;

import com.uc_it4045.assignment_tracker.dto.Assignment;
import com.uc_it4045.assignment_tracker.service.IAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AssignmentTrackerController {

    @Autowired
    IAssignmentService assignmentService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

        /**
         * Handle the / endpoint
         * @return Start page template
         */
        @RequestMapping("/")
        public String index() {
            return "start";
        }

        @GetMapping("/api/assignments")
        public ResponseEntity fetchAllAssignments() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            List<Assignment> fetchedAssignments = assignmentService.fetchAll();
            if (fetchedAssignments.isEmpty()) {
                List<Assignment> assignments = new ArrayList<Assignment>();
                Assignment asgn01 = new Assignment();
                Assignment asgn02 = new Assignment();
                Assignment asgn03 = new Assignment();
                asgn01.setTitle("Assignment01");
                asgn02.setTitle("Assignment02");
                asgn03.setTitle("Assignment03");
                asgn01.setStatus(Status.PENDING);
                asgn02.setStatus(Status.PENDING);
                asgn03.setStatus(Status.PENDING);
                asgn01.setDate(LocalDateTime.now());
                asgn02.setDate(LocalDateTime.now());
                asgn03.setDate(LocalDateTime.now());
                return new ResponseEntity(assignments, headers, HttpStatus.OK);
            }
            return new ResponseEntity(fetchedAssignments, headers, HttpStatus.OK);
        }

        @GetMapping("/api/assignments/{id}")
        public ResponseEntity fetchAssignmentById(@PathVariable("id") int id) {
            Assignment assignment = assignmentService.fetchById(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(assignment, headers, HttpStatus.OK);
        }

        @PostMapping(value="/api/assignments", consumes="application/json", produces="application/json")
        public ResponseEntity createAssignment(@RequestBody Assignment assignment) {
            Assignment newAssignment = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            try {
                newAssignment = assignmentService.save(assignment);
                return new ResponseEntity(newAssignment, headers, HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Unable to save assignment: " + e.getMessage(), e);
                return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PostMapping(value="/api/assignments/{id}", consumes="application/json", produces="application/json")
        public ResponseEntity updateAssignment(@PathVariable("id") int id, @RequestBody Assignment assignment) {
            Assignment foundAssignment = assignmentService.fetchById(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            try {
                Assignment updatedAssignment = assignmentService.save(foundAssignment);
                return new ResponseEntity(updatedAssignment, headers, HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Unable to update assignment: " + e.getMessage(), e);
                return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @DeleteMapping("/api/assignments/{id}")
        public ResponseEntity deleteAssignment(@PathVariable("in") int id) {
            try {
                assignmentService.delete(id);
                logger.info("Assigment with ID: " + id + " was deleted.");
                return new ResponseEntity(HttpStatus.OK);
            } catch (Exception e) {
                logger.error("OPERATION FAILED: Unable to delete assignment ID: " + id +", message: " + e.getMessage(), e);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
