package com.example.oodp_tp_app.classes;

import java.util.ArrayList;
import java.util.Date;

public abstract class Task {
    private String title;
    private State state;
    private ArrayList<Comment> comments;
    private Date dueDate;

    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void stateUpdate() {

    }

    public void writeComment() {

    }

    public void delete() {

    }

    public void edit() {

    }
}
