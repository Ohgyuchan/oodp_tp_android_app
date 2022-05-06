package com.example.oodp_tp_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oodp_tp_app.R;
import com.example.oodp_tp_app.classes.MainTask;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<MainTask> tasks;
    private Context context;

    public TaskAdapter(ArrayList<MainTask> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        TaskViewHolder holder = new TaskViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.tv_task_name.setText(tasks.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return (tasks != null ? tasks.size() : 0);
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tv_task_name;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_task_name = itemView.findViewById(R.id.tv_task_name);
        }
    }
}
