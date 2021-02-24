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
import com.example.antssignments.Models.Assignments;
import com.example.antssignments.Models.Courses;
import com.example.antssignments.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class AssignmentFragment extends Fragment {
    public static final String ASSIGNMENTS_FROM_COURSE = "https://canvas.eee.uci.edu/api/v1/courses/%d/assignments?access_token=4407~UeskhdnHkzhYvPj5UxZFwJFTDhZcJJwaf98sJRP4loywfWHYvldN4HFPmxLOAuUV";
    public static final String TAG = "AssignmentFragment";

    protected AssignmentAdapter adapter;
    private RecyclerView rvAssignments;
    protected ArrayList<Courses> courseList;

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

        Bundle extras = this.getArguments();//getActivity().getIntent().getExtras();
        ArrayList<Courses> courseList  = extras.getParcelableArrayList("courseList");
        Log.i(TAG, "Courses: " + courseList.toString());


        adapter = new AssignmentAdapter(getContext(), courseList);
        rvAssignments.setAdapter(adapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assignment, container, false);

    }


}