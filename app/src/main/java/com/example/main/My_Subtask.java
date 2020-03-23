package com.example.main;

import java.util.Date;

public class My_Subtask {
    private String Name, Status , Ndate;
    private Date date;

    public My_Subtask(String name, String status, String ndate, Date date) {
        Name = name;
        Status = status;
        Ndate = ndate;
        this.date = date;
    }

    public My_Subtask(String name, String status, String ndate) {
        Name = name;
        Status = status;
        Ndate = ndate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getNdate() {
        return Ndate;
    }

    public void setNdate(String ndate) {
        Ndate = ndate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
