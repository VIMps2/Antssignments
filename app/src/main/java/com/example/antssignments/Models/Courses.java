package com.example.antssignments.Models;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Courses implements Parcelable {
    private static ArrayList<Assignments> assignmentList;

    public Courses() {
    }

    String courseName;
    int courseID;

    protected Courses(android.os.Parcel in) {
        courseName = in.readString();
        courseID = in.readInt();
    }

    public static final Creator<Courses> CREATOR = new Creator<Courses>() {
        @Override
        public Courses createFromParcel(android.os.Parcel in) {
            return new Courses(in);
        }

        @Override
        public Courses[] newArray(int size) {
            return new Courses[size];
        }
    };

    public static void setAssignmentList(ArrayList<Assignments> assignmentList) {
        Courses.assignmentList = assignmentList;
    }

    public String getCourseName() {
        return courseName;
    }

    public static ArrayList<Assignments> getAssignmentList() {
        return assignmentList;
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

    public static Courses fromJson(JSONObject jsonObject) throws JSONException {
        Courses course = new Courses();
        course.courseName = jsonObject.getString("name");
        course.courseID = jsonObject.getInt("id");
        return course;
    }

    public static List<Courses> fromJsonArray(JSONArray coursesJsonArray) throws JSONException {
        List<Courses> courses = new ArrayList<>();
        for (int i = 0; i < coursesJsonArray.length(); i++) {
            JSONObject obj = coursesJsonArray.getJSONObject(i);
            if (obj.has("access_restricted_by_date")) {
                continue;
            }
            courses.add(fromJson(coursesJsonArray.getJSONObject(i)));
        }
        return courses;
    }

    public static void addAssignments(List<Assignments> assignments) {
        assignmentList.addAll(assignments);
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