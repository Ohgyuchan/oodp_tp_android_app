package com.example.oodp_tp_app.classes;

import java.util.ArrayList;
import java.util.Date;

public abstract class Task {
    private String title;
    private State state;
    private ArrayList<Comment> comments;
    private Date dueDate;

    public void stateUpdate() {

    }

    public void writeComment() {

    }

    public void delete() {

    }

    public void edit() {

    }
}
