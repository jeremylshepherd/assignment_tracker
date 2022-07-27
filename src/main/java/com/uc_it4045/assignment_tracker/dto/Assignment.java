package com.uc_it4045.assignment_tracker.dto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Assignment {
    int Id;
    String Title;
    String Description;
    double Points;
    String User;
    String Date;


    public void setId (int Id){
        this.Id=Id;
    }

    public void setTitle(String Title){
        this.Title=Title;
    }

    public void setDescription(String Description){
        this.Description=Description;
    }

    public void setPoints (double Points){
        this.Points=Points;
    }

    public void setUser (String User){
        this.User=User;
    }

    public void setDate (String Date){
        this.Date=Date;
    }


}
