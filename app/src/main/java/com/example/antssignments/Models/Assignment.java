package com.example.antssignments.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
    String assignmentName;
    String dueDate;


    public Assignment() {
    }

    public Assignment(JSONObject jsonObject) throws JSONException {
        assignmentName = jsonObject.getString("name");
        dueDate = jsonObject.getString("due_at");
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public static List<Assignment> fromJsonArray(JSONArray assignmentsJsonArray) throws JSONException {
        List<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < assignmentsJsonArray.length(); i++) {
            JSONObject obj = assignmentsJsonArray.getJSONObject(i);
            assignments.add(new Assignment(obj));
        }
        return assignments;
    }
}

