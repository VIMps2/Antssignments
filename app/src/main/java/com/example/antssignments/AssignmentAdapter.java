package com.example.antssignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antssignments.Fragments.AssignmentFragment;
import com.example.antssignments.Models.Assignments;
import com.example.antssignments.Models.Courses;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private Context context;
    private List<Courses> courses;
    private List<Assignments> assignments;

    public AssignmentAdapter(Context context, List<Courses> courses, List<Assignments> assignments) {
        this.context = context;
        this.courses = courses;
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_assignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Courses course = courses.get(position);
        Assignments assignment;
        assignment = assignments.get(position);
        holder.bind(course, assignment);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView AssignmentName;
        private TextView ClassName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AssignmentName = itemView.findViewById(R.id.tvAssignmentName);
            ClassName = itemView.findViewById(R.id.tvClassName);
        }

        public void bind(Courses course, Assignments assignments) {
            ClassName.setText(course.getCourseName());
            AssignmentName.setText(assignments.getAssignmentName());
        }

    }
}
