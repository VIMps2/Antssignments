package com.example.antssignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antssignments.Models.Assignments;
import com.example.antssignments.Models.Courses;

import java.util.HashMap;
import java.util.List;

public class AssignmentChildAdapter extends RecyclerView.Adapter<AssignmentChildAdapter.ViewHolder>{
    private HashMap<Courses, List<Assignments>> assignmentsList;
    public AssignmentChildAdapter(List<Assignments> assignmentsList) {
        this.assignmentsList = (HashMap<Courses, List<Assignments>>) assignmentsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentChildAdapter.ViewHolder holder, int position) {
        List<Assignments> assignments = assignmentsList.get(position);
        holder.bind(assignments);
    }

    @Override
    public int getItemCount() {
        return assignmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView AssignmentName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AssignmentName = itemView.findViewById(R.id.child_assignment);
        }

        public void bind(List<Assignments> assignments) {
            for (int i = 0; i < assignments.size(); i++) {
                AssignmentName.setText(assignments.get(i).getAssignmentName());
            }
        }
    }
}
