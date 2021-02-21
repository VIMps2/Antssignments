package com.example.antssignments.Models;

import android.annotation.SuppressLint;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
@Parcel
public class Courses {
    public Courses() {
    }

    String courseName;
    int courseID;

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

    public Courses(JSONObject jsonObject) throws JSONException {
        courseName = jsonObject.getString("name");
        courseID = jsonObject.getInt("id");

    }

    public static List<Courses> fromJsonArray(JSONArray coursesJsonArray) throws JSONException {
        List<Courses> courses = new ArrayList<>();
        for (int i = 0; i < coursesJsonArray.length(); i++) {
            JSONObject obj = coursesJsonArray.getJSONObject(i);
            if (obj.has("access_restricted_by_date")) {
                continue;
            }
            courses.add(new Courses(obj));
        }
        return courses;
    }
}