package com.example.oodp_tp_app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oodp_tp_app.R;
import com.example.oodp_tp_app.adapter.ProjectAdapter;
import com.example.oodp_tp_app.adapter.TaskAdapter;
import com.example.oodp_tp_app.classes.MainTask;
import com.example.oodp_tp_app.classes.Project;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProjectDetail extends AppCompatActivity {

    private TextView project_name;
    private Button btn_task_create;
    private FirebaseFirestore db;
    private CollectionReference taskCollection;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MainTask> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        project_name = findViewById(R.id.project_name);
        btn_task_create = findViewById(R.id.btn_task_create);
        project_name.setText(getIntent().getStringExtra("projectName"));
        String docId = getIntent().getStringExtra("projectId");

        btn_task_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskCreateActivity.class);
                intent.putExtra("mode", "task");
                intent.putExtra("docId", docId);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });



        recyclerView = findViewById(R.id.task_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        tasks = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        taskCollection = db.collection("Projects").document(docId).collection("Tasks");
        taskCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot taskSnapshot : task.getResult()) {

                        MainTask mainTask = taskSnapshot.toObject(MainTask.class);
                        tasks.add(mainTask);

                        Log.d("Tasks Read::", taskSnapshot.getId() + " => " + taskSnapshot.getData());

                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("Projects Read", "Error getting documents: ", task.getException());
                }
            }
        });
        adapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(adapter);
    }
}