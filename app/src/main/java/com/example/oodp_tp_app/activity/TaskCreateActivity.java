package com.example.oodp_tp_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oodp_tp_app.R;
import com.example.oodp_tp_app.classes.Comment;
import com.example.oodp_tp_app.classes.Leader;
import com.example.oodp_tp_app.classes.Member;
import com.example.oodp_tp_app.classes.State;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskCreateActivity extends AppCompatActivity {
    private EditText et;
    private Button btn;
    private FirebaseFirestore db;
    private CollectionReference collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        et = findViewById(R.id.et_task_name);
        btn = findViewById(R.id.btn_task_add);

        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        if (mode.compareTo("project") == 0 ){
            et.setHint("Project Name");
        } else {
            et.setHint("Task Name");
        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mode.compareTo("project") == 0){
                    ArrayList<Member> members = new ArrayList<>();
                    members.add(new Member(FirebaseAuth.getInstance().getCurrentUser().getUid(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString()));
                    Map<String, Object> project = new HashMap<>();
                    String docId = db.collection("Projects").document().getId();
                    project.put("projectId", docId);
                    project.put("projectName", et.getText().toString());
                    project.put("leader", new Leader(FirebaseAuth.getInstance().getCurrentUser().getUid(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString()));
                    project.put("members", members);
                    db.collection("Projects").document(docId).set(project);
                    finish();
                } else {
                    Map<String, Object> task = new HashMap<>();
                    task.put("title", et.getText().toString());
                    String projectId = intent.getStringExtra("docId");
                    db.collection("Projects").document(projectId).collection("Tasks").add(task);
                    finish();
                }
            }
        });
    }
}