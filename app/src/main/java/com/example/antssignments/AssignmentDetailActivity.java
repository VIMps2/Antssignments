package com.example.antssignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AssignmentDetailActivity extends AppCompatActivity {

    TextView tvsssignmentTitle, tvassignmentDescription, tvdueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);

        tvsssignmentTitle = findViewById(R.id.tvAssignmentTitle);
        tvassignmentDescription = findViewById(R.id.tvassignmentDescription);
        tvdueDate = findViewById(R.id.tvdueDate);


        //ToDo: Retrive Parcelable object passed into this intent, that object
        // will represent a single assignment, parse data and assign text views

    }
}