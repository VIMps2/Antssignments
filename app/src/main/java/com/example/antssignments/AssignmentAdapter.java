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
    protected ArrayList<Course> cours;
    private List<Assignment> assignmentList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    HashMap<Course, List<Assignment>> coursesToAssignments = new HashMap<>();


    public AssignmentAdapter(Context context, ArrayList<Course> cours) {
        this.context = context;
        this.cours = cours;
        createAssignments(cours);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_assignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = cours.get(position);
        holder.ClassName.setText(course.getCourseName());

        //LinearLayoutManager layoutManager = new LinearLayoutManager(holder.ChildRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        //layoutManager.setInitialPrefetchItemCount(coursesToAssignments.size());

        /*
        for(Map.Entry<Courses, List<Assignments>> coursesListHashMap : coursesToAssignments.entrySet()) {
            for (int j = 0; j < coursesToAssignments.size(); j++) {
                Courses key = coursesListHashMap.getKey();
                AssignmentChildAdapter assignmentChildAdapter = new AssignmentChildAdapter(coursesToAssignments.get(key));
                holder.ChildRecyclerView.setLayoutManager(layoutManager);
                holder.ChildRecyclerView.setAdapter(assignmentChildAdapter);
                holder.ChildRecyclerView.setRecycledViewPool(viewPool);
            }
        }

         */
    }

    @Override
    public int getItemCount() {
        return cours.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ClassName;
        //private RecyclerView ChildRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ClassName = itemView.findViewById(R.id.tvClassName);
            //ChildRecyclerView = itemView.findViewById(R.id.rvChildRecyclerView);

        }

    }
    @SuppressLint("DefaultLocale")
    public void createAssignments(ArrayList<Course> courseList) {
        String ASSIGNMENTS_FROM_COURSE = "https://canvas.eee.uci.edu/api/v1/courses/%d/assignments?access_token=4407~UeskhdnHkzhYvPj5UxZFwJFTDhZcJJwaf98sJRP4loywfWHYvldN4HFPmxLOAuUV";
        AssignmentChildAdapter adapter;
        AsyncHttpClient client = new AsyncHttpClient();
        for (int i = 0; i < courseList.size(); i++) {
            Course courseObject = courseList.get(i);
            int ID = courseObject.getCourseID();
            client.get(String.format(ASSIGNMENTS_FROM_COURSE, ID), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Headers headers, JSON json) {
                    Log.d(TAG, "onSuccess");
                    JSONArray assignments = json.jsonArray;
                    try {
                        assignmentList = Assignment.fromJsonArray(assignments);
                        coursesToAssignments.put(courseObject, assignmentList);
                        Log.d(TAG, "HashMap: " + coursesToAssignments.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                    Log.e(TAG, "onFailure", throwable);
                }
            });
        }
    }


}
