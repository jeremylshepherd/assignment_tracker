package com.uc_it4045.assignment_tracker.UserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

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
        
        @GetMapping("/")
        public GetAssignment<"Assignment">("/"){
            return "assignment";
        }


    }
