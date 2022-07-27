package com.uc_it4045.assignment_tracker.dto;

import lombok.Data;

public @Data class TaskProgress {
    private String task;
    private String toDo;
    private String inProgress;
    private String complete;
}
