package com.example.antssignments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.antssignments.Models.Assignments;
import com.example.antssignments.Models.Courses;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class AssignmentsActivity extends AppCompatActivity {

    public static final String ASSIGNMENTS_FROM_COURSE = "https://canvas.eee.uci.edu/api/v1/courses/%d/assignments?access_token=4407~UeskhdnHkzhYvPj5UxZFwJFTDhZcJJwaf98sJRP4loywfWHYvldN4HFPmxLOAuUV";
    public static final String TAG = "AssignmentsActivity";

    List<Courses> courseList;
    List<Assignments> assignmentsList;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        courseList = new ArrayList<>();

        int courseLength = getIntent().getIntExtra("listSize", 0);
        for (int i = 0; i < courseLength; i++) {
            Courses course = (Courses) Parcels.unwrap(getIntent().getParcelableExtra("courseList" + String.valueOf(i)));
            courseList.add(course);
        }

        Log.i(TAG, "Courses: " + courseList.toString());

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
