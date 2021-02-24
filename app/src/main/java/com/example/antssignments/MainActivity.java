package com.example.antssignments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.antssignments.Fragments.AssignmentFragment;
import com.example.antssignments.Models.Assignments;
import com.example.antssignments.Models.Courses;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "AssignmentsActivity";
    List<Courses> courseList;
    List<Assignments> assignmentsList;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        AssignmentFragment assignmentFragment = new AssignmentFragment();
        courseList = new ArrayList<>();


        int courseLength = getIntent().getIntExtra("listSize", 0);
        for (int i = 0; i < courseLength; i++) {
            Courses course = (Courses) Parcels.unwrap(getIntent().getParcelableExtra("courseList" + String.valueOf(i)));
            courseList.add(course);

        }
        Log.i(TAG, "Courses: " + courseList.toString());

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("courseList", (ArrayList<? extends Parcelable>) courseList);
        assignmentFragment.setArguments(bundle);


    }






}
