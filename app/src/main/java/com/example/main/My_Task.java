package com.example.main;

import java.util.Date;
import java.util.List;

public class My_Task extends My_Subtask {

    private List<My_Subtask> list_sub;
    private String priority;


    public My_Task(String name, String status, String ndate, Date date) {
        super(name, status, ndate, date);
    }
    public My_Task(String name, String status, String ndate, Date date,List<My_Subtask> list_sub ) {
        super(name, status, ndate, date);
        this.list_sub = list_sub;
    }

    public My_Task(String name, String status, String ndate, List<My_Subtask> list_sub) {
        super(name, status, ndate);
        this.list_sub = list_sub;
    }
    public My_Task(String name, String status, String ndate) {
        super(name, status, ndate);
    }

    public List<My_Subtask> getList_sub() {
        return list_sub;
    }

    public void setList_sub(List<My_Subtask> list_sub) {
        this.list_sub = list_sub;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
