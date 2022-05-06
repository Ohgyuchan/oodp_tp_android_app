package com.example.oodp_tp_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oodp_tp_app.R;

public class TaskCreateActivity extends AppCompatActivity {
    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        et = findViewById(R.id.et_task_name);
        btn = findViewById(R.id.btn_task_add);

        Intent intent = getIntent();

        if (intent.getStringExtra("mode") == "project"){
            et.setText("Project Name");

        } else {
            et.setText("Create Name");
        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent.getStringExtra("mode") == "project"){
                    Intent intent = new Intent(getApplicationContext(), TaskCreateActivity.class);

                } else {
                    et.setText("Create Name");
                }
            }
        });
    }
}