package com.uc_it4045.assignment_tracker.dto;

import com.uc_it4045.assignment_tracker.Status;

import java.time.LocalDateTime;

public class Assignment {
    private String title;
    private Status status;
    private LocalDateTime date;

    public Assignment(String title, Status status, LocalDateTime date) {
        this.title = title;
        this.status = status;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }
}

