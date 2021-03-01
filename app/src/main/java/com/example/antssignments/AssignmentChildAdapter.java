package com.example.antssignments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antssignments.Models.Assignment;
import com.example.antssignments.Models.Course;

import java.util.HashMap;
import java.util.List;

public class AssignmentChildAdapter extends RecyclerView.Adapter<AssignmentChildAdapter.ViewHolder>{
    private HashMap<Course, List<Assignment>> assignmentsList;
    public AssignmentChildAdapter(List<Assignment> assignmentList) {
        this.assignmentsList = (HashMap<Course, List<Assignment>>) assignmentList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentChildAdapter.ViewHolder holder, int position) {
        List<Assignment> assignments = assignmentsList.get(position);
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

        public void bind(List<Assignment> assignments) {
            for (int i = 0; i < assignments.size(); i++) {
                AssignmentName.setText(assignments.get(i).getAssignmentName());
            }
        }
    }
}
