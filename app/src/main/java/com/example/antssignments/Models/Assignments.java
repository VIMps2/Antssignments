package com.example.antssignments.Models;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class Assignments {
    String assignmentName;
    String dueDate;


    public Assignments() {
    }

    public Assignments(JSONObject jsonObject) throws JSONException {
        assignmentName = jsonObject.getString("name");
        dueDate = jsonObject.getString("due_at");
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public static List<Assignments> fromJsonArray(JSONArray assignmentsJsonArray) throws JSONException {
        List<Assignments> assignments = new ArrayList<>();
        for (int i = 0; i < assignmentsJsonArray.length(); i++) {
            JSONObject obj = assignmentsJsonArray.getJSONObject(i);
            assignments.add(new Assignments(obj));
        }
        return assignments;
    }
}

