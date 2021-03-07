package com.example.antssignments.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.antssignments.AssignmentAdapter;
import com.example.antssignments.Models.Assignment;
import com.example.antssignments.Models.Course;
import com.example.antssignments.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import okhttp3.Headers;

public class AssignmentFragment extends Fragment {
    public static final String ACCESS_TOKEN = "4407~UeskhdnHkzhYvPj5UxZFwJFTDhZcJJwaf98sJRP4loywfWHYvldN4HFPmxLOAuUV";
    public static final String ASSIGNMENTS_FROM_COURSE = "https://canvas.eee.uci.edu/api/v1/courses/%d/assignments?access_token=%s";
    public static final String ALL_COURSE_IDS = "https://canvas.eee.uci.edu/api/v1/courses?access_token=%s";
    public static final String TAG = "AssignmentFragment";

    protected AssignmentAdapter adapter;
    private RecyclerView rvAssignments;
    private ArrayList<Course> courseList;
    private ArrayList<Assignment> assignmentList;

    public AssignmentFragment() {
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "AssignmentsFragment launched");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvAssignments = view.findViewById(R.id.rvAssignments);
        courseList = new ArrayList<>();
        assignmentList = new ArrayList<>();
        createCourses();

        adapter = new AssignmentAdapter(getContext(), assignmentList, courseList);
        rvAssignments.setAdapter(adapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assignment, container, false);

    }

    private void createCourses() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(ALL_COURSE_IDS, ACCESS_TOKEN), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONArray courses = json.jsonArray;
                try {
                    courseList = Course.fromJsonArray(courses);
                    createAssignments();
                    Log.i(TAG, "Courses: " + courseList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    public void createAssignments() {
        AsyncHttpClient client = new AsyncHttpClient();
        for (int i = 0; i < courseList.size(); i++) {
            Course courseObject = courseList.get(i);
            int ID = courseObject.getCourseID();
            client.get(String.format(ASSIGNMENTS_FROM_COURSE, ID, ACCESS_TOKEN), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Headers headers, JSON json) {
                    Log.d(TAG, "onSuccess");
                    JSONArray assignments = json.jsonArray;
                    try {
                        assignmentList.addAll(Assignment.fromJsonArray(assignments));
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "Assignments: " + assignmentList.toString());
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