package com.example.antssignments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.antssignments.Fragments.AssignmentFragment;
import com.example.antssignments.Models.Assignments;
import com.example.antssignments.Models.Courses;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private Context context;
    protected ArrayList<Courses> courses;
    private List<Assignments> assignmentsList;
    public static final String TAG = "AssignmentAdapter";

    public AssignmentAdapter(Context context, ArrayList<Courses> courses) {
        this.context = context;
        this.courses = courses;
        createAssignments(courses);

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
        holder.bind(course);
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

        public void bind(Courses course) {
            ClassName.setText(course.getCourseName());
            //AssignmentName.setText();
        }

    }
    @SuppressLint("DefaultLocale")
    public void createAssignments(ArrayList<Courses> courseList) {
        String ASSIGNMENTS_FROM_COURSE = "https://canvas.eee.uci.edu/api/v1/courses/%d/assignments?access_token=4407~UeskhdnHkzhYvPj5UxZFwJFTDhZcJJwaf98sJRP4loywfWHYvldN4HFPmxLOAuUV";
        AsyncHttpClient client = new AsyncHttpClient();
        for (int i = 0; i < courseList.size(); i++) {
            int ID = courseList.get(i).getCourseID();
            client.get(String.format(ASSIGNMENTS_FROM_COURSE, ID), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Headers headers, JSON json) {
                    Log.d(TAG, "onSuccess");
                    JSONArray assignments = json.jsonArray;
                    try {
                        assignmentsList = Assignments.fromJsonArray(assignments);
                        Log.i(TAG, "Assignments: " + assignmentsList.toString());
                        //Courses.addAssignments(assignmentsList);

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
