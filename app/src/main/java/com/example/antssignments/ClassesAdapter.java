package com.example.antssignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antssignments.Fragments.NoteFragment;
import com.example.antssignments.Models.Courses;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {
    public interface OnClickListener {
        void OnItemClicked(int position);
    }

    private Context context;
    private ArrayList<Courses> courses;

    OnClickListener clickListener;

    public ClassesAdapter(Context context, ArrayList<Courses> courses, OnClickListener  clickListener) {
        this.context = context;
        this.courses = courses;
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
            CourseName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.OnItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
