package com.example.oodp_tp_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oodp_tp_app.R;
import com.example.oodp_tp_app.classes.Project;
import com.example.oodp_tp_app.classes.Member;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private ArrayList<Project> projects;
    private Context context;

    public ProjectAdapter(ArrayList<Project> projects, Context context) {
        this.projects = projects;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
        ProjectViewHolder holder = new ProjectViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.tv_project_name.setText(projects.get(position).getProjectName());
        holder.tv_leader_name.setText(projects.get(position).getLeader().getDisplayName());
        holder.tv_member_count.setText(String.valueOf(projects.get(position).getMembersCount()));
    }

    @Override
    public int getItemCount() {
        return (projects != null ? projects.size() : 0);
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView tv_project_name;
        TextView tv_leader_name;
        TextView tv_member_count;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_project_name = itemView.findViewById(R.id.tv_project_name);
            this.tv_leader_name = itemView.findViewById(R.id.tv_leader_name);
            this.tv_member_count = itemView.findViewById(R.id.tv_member_count);

        }
    }
}
