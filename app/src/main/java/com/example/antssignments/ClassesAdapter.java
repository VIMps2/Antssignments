package com.example.antssignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antssignments.Models.Courses;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Courses> courses;

    public ClassesAdapter(Context context, ArrayList<Courses> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Courses course = courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CourseName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CourseName = itemView.findViewById(R.id.tvCourse);
        }

        public void bind(Courses course) {
            CourseName.setText(course.getCourseName());
        }
    }
}
