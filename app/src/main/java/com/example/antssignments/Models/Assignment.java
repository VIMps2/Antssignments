package com.example.antssignments.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Assignment {

    String assignmentName;
    String dueDate;
    int courseID;
    GregorianCalendar assignmentDue;

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
        int year = parseInt(dueDate.substring(0,4));
        int month = parseInt(dueDate.substring(5,7));
        int day = parseInt(dueDate.substring(8,10));
        int hour = parseInt(dueDate.substring(11,13));
        int min = parseInt(dueDate.substring(14,16));
        int sec = parseInt(dueDate.substring(17,19));
        assignmentDue = new GregorianCalendar(year, month, day, hour, min, sec);
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public GregorianCalendar getAssignmentDue() {return assignmentDue; }

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

        Comparator<Assignment> compareByDueDate = (Assignment a1, Assignment a2) -> a1.getAssignmentDue().compareTo(a2.getAssignmentDue());
        Collections.sort(assignments, compareByDueDate);

        return assignments;
    }
}

