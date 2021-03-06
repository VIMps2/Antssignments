package com.example.antssignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antssignments.Models.Course;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {
    public interface OnClickListener {
        void OnItemClicked(String position);
    }

    private Context context;
    private ArrayList<Course> cours;

    OnClickListener clickListener;

    public ClassesAdapter(Context context, ArrayList<Course> cours, OnClickListener  clickListener) {
        this.context = context;
        this.cours = cours;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = cours.get(position);
        holder.bind(course);

    }

    @Override
    public int getItemCount() {
        return cours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CourseName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CourseName = itemView.findViewById(R.id.tvCourse);
        }

        public void bind(Course course) {
            CourseName.setText(course.getCourseName());
            CourseName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.OnItemClicked(course.getCourseName());
                }
            });
        }
    }
}
