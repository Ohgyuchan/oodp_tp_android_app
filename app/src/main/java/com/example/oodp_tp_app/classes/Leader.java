package com.example.oodp_tp_app.classes;

import java.util.ArrayList;

public class Leader extends Member{
    public Leader() {
        super();
    }
    public Leader(String uid, String email, String displayName, String photoUrl, ArrayList<Project> projects) {
        super(uid, email, displayName, photoUrl, projects);
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }
}
