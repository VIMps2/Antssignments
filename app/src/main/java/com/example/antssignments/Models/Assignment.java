package com.example.antssignments.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Assignment {

    String assignmentName;
    String dueDate;
    int courseID;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Assignment(JSONObject jsonObject) throws JSONException {
        assignmentName = jsonObject.getString("name");
        dueDate = jsonObject.getString("due_at");
        courseID = jsonObject.getInt("course_id");

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
            //Log.d("AssignmentClass", "Due date: " + obj.getString("due_at"));
            if (obj.has("due_at") && obj.isNull("due_at")) {
                continue;
            }
            assignments.add(new Assignment(obj));
        }
        return assignments;
    }
}

