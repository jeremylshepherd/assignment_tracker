package com.uc_it4045.assignment_tracker.dto;

public class TaskProgress {
    String task;
    String toDo;
    String inProgress;
    String complete;

    public void setTask(String task) {
        this.task = task;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public void setInProgress(String inProgress) {
        this.inProgress = inProgress;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getTask() {
        return task;
    }

    public String getToDo() {
        return toDo;
    }

    public String getInProgress() {
        return inProgress;
    }

    public String getComplete() {
        return complete;
    }
}
