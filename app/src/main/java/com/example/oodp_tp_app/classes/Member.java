package com.example.oodp_tp_app.classes;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Member {
    private String uid;
    private String email;
    private String displayName;
    private String photoUrl;
    private ArrayList<Project> projects;

    public Member() {

    }

    public Member(String uid, String email, String displayName, String photoUrl, ArrayList<Project> projects) {
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.photoUrl = photoUrl;
        this.projects = projects;
    }

    public Member(String uid, String email, String displayName, String photoUrl) {
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.photoUrl = photoUrl;
        this.projects = new ArrayList<>();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setProjectsFromStringList(ArrayList<String> projects) {
        ArrayList<Project> projectArrayList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for(String project : projects){
            db.collection("Projects").document(project).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Map<String, Object> data = documentSnapshot.getData();
                    Project kProject = new Project();
                    assert data != null;
                    kProject.setProjectName((String) data.get("projectName"));
                    kProject.setLeaderFromString((String) data.get("leader"));
                    kProject.setMembersFromStringList((ArrayList<String>) data.get("members"));
                    projectArrayList.add(kProject);
                }
            });
        }
        setProjects(projectArrayList);
    }
}
