package com.example.antssignments.Models;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Course implements Parcelable {
    public static ArrayList<Assignment> listAssignments;


    public Course() {
    }

    String courseName;
    int courseID;

    protected Course(android.os.Parcel in) {
        courseName = in.readString();
        courseID = in.readInt();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(android.os.Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };


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

    public static void addAssignments(List<Assignment> assignments) {
        listAssignments.addAll(assignments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {

        dest.writeString(courseName);
        dest.writeInt(courseID);
    }
}