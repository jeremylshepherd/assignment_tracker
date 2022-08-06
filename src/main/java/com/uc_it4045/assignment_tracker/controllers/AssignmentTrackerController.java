package com.uc_it4045.assignment_tracker.controllers;
import com.uc_it4045.assignment_tracker.service.IAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            return "index";
        }

    }
