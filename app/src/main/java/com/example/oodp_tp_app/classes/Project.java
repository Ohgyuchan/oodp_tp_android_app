package com.example.oodp_tp_app.classes;

import java.util.ArrayList;

public class Project {
    private String projectName;
    private ArrayList<Member> members;
    private Leader leader;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public int getMembersCount() {
        return members.size();
    }

    public void setUsers(ArrayList<Member> members) {
        this.members = members;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }
}
