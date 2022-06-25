package com.uc_it4045.assignment_tracker.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    }
