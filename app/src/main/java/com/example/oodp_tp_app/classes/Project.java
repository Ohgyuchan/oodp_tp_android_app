package com.example.oodp_tp_app.classes;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Project {
    private String projectName;
    private ArrayList<Member> members;
    private Leader leader;

    public Project() {

    }

    public Project(String projectName, ArrayList<Member> members, Leader leader) {
        this.projectName = projectName;
        this.members = members;
        this.leader = leader;
    }

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

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public void setLeader(String leader) {
        final Leader[] kLeader = new Leader[1];
        FirebaseFirestore.getInstance().collection("Users").document(leader).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                kLeader[0] = documentSnapshot.toObject(Leader.class);
            }
        });
        setLeader(kLeader[0]);
    }

    public void setMembersFromString(ArrayList<String> members) {
        ArrayList<Member> memberArrayList = new ArrayList<>();
        for(String member : members){
            FirebaseFirestore.getInstance().collection("Users").document(member).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Member kMember = documentSnapshot.toObject(Member.class);
                    memberArrayList.add(kMember);
                }
            });
        }
        setMembers(memberArrayList);
    }
}
