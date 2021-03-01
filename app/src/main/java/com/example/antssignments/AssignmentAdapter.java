package com.example.antssignments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.antssignments.Models.Assignment;
import com.example.antssignments.Models.Course;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {
    public static final String TAG = "AssignmentAdapter";
    private Context context;
    private List<Assignment> assignmentList;
    private List<Course> courseList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public AssignmentAdapter(Context context, ArrayList<Assignment> assignmentList, ArrayList<Course> courseList) {
        this.context = context;
        this.assignmentList = assignmentList;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_assignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignment assignment = assignmentList.get(position);
        holder.bind(assignment);
    }

    @Override
    public int getItemCount(){
        return assignmentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAssignmentName, tvCourseName, tvDueDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAssignmentName = itemView.findViewById(R.id.tvAssignmentName);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
        }

        public void bind(Assignment assignment){
            tvAssignmentName.setText(assignment.getAssignmentName());
            tvDueDate.setText(assignment.getDueDate());

            for(int i = 0; i < courseList.size(); i++ ){
                if(assignment.getCourseID() ==  courseList.get(i).getCourseID()){
                    tvCourseName.setText(courseList.get(i).getCourseName());
                }
            }
        }
    }
}
