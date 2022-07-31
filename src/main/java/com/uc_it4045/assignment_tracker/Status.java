package com.uc_it4045.assignment_tracker;

public enum Status {
    PENDING("pending"),
    INPROGRESS("inprogress"),
    COMPLETE("complete");

    public final String label;

    private Status(String label) {
        this.label = label;
    }

}
