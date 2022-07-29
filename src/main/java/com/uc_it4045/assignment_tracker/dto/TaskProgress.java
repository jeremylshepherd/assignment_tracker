package com.uc_it4045.assignment_tracker.dto;

import lombok.Data;

public @Data class TaskProgress {
    String task;
    String toDo;
    String inProgress;
    String complete;

}
