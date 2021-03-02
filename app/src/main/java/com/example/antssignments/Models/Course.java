package com.example.antssignments.Models;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Course{
    public static ArrayList<Assignment> listAssignments;


    public Course() {
    }

    String courseName;
    int courseID;

    protected Course(android.os.Parcel in) {
        courseName = in.readString();
        courseID = in.readInt();
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public static Course fromJson(JSONObject jsonObject) throws JSONException {
        Course course = new Course();
        course.courseName = jsonObject.getString("name");
        course.courseID = jsonObject.getInt("id");
        return course;
    }

    public static ArrayList<Course> fromJsonArray(JSONArray coursesJsonArray) throws JSONException {
        ArrayList<Course> cours = new ArrayList<>();
        for (int i = 0; i < coursesJsonArray.length(); i++) {
            JSONObject obj = coursesJsonArray.getJSONObject(i);
            if (obj.has("access_restricted_by_date")) {
                continue;
            }
            cours.add(fromJson(coursesJsonArray.getJSONObject(i)));
        }
        return cours;
    }


}