package com.example.oodp_tp_app.classes;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

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

    public void setLeaderFromString(String leader) {
        ArrayList<Leader> leaderArrayList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(leader).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> data = documentSnapshot.getData();
                Leader kLeader = new Leader();
                assert data != null;
                kLeader.setUid((String) data.get("uid"));
                kLeader.setEmail((String) data.get("email"));
                kLeader.setEmail((String) data.get("displayName"));
                kLeader.setEmail((String) data.get("photoUrl"));
                kLeader.setProjectsFromStringList((ArrayList<String>) data.get("projects"));
                leaderArrayList.add(kLeader);
            }
        });
        setLeader(leaderArrayList.get(0));
    }

    public void setMembersFromStringList(ArrayList<String> members) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Member> memberArrayList = new ArrayList<>();
        for(String member : members){
            System.out.println(member);
            db.collection("Users").document(member).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        Map<String, Object> data = documentSnapshot.getData();
                        Member kMember = new Member();
                        assert data != null;
                        kMember.setUid((String) data.get("uid"));
                        kMember.setEmail((String) data.get("email"));
                        kMember.setEmail((String) data.get("displayName"));
                        kMember.setEmail((String) data.get("photoUrl"));
                        kMember.setProjectsFromStringList((ArrayList<String>) data.get("projects"));
                        memberArrayList.add(kMember);
                    }
                }
            });
        }
        setMembers(memberArrayList);
    }
}
