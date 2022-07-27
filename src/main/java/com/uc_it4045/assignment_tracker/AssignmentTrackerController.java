package com.uc_it4045.assignment_tracker;
import com.uc_it4045.assignment_tracker.dto.Assignment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AssignmentTrackerController {
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
            List<Assignment> assignments = new ArrayList<Assignment>();
            assignments.add(new Assignment("Assignment 01", Status.PENDING, LocalDateTime.now()));
            assignments.add(new Assignment("Assignment 02", Status.PENDING, LocalDateTime.now()));
            assignments.add(new Assignment("Assignment 03", Status.PENDING, LocalDateTime.now()));
            return new ResponseEntity(assignments, headers, HttpStatus.OK);
        }
    }
