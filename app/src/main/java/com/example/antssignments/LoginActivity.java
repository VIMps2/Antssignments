package com.example.antssignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.antssignments.Models.Courses;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class LoginActivity extends AppCompatActivity {

    public static final String ALL_COURSE_IDS = "https://canvas.eee.uci.edu/api/v1/courses?access_token=4407~UeskhdnHkzhYvPj5UxZFwJFTDhZcJJwaf98sJRP4loywfWHYvldN4HFPmxLOAuUV";
    public static final String TAG = "LoginActivity";

    private ArrayList<Courses> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createCourses();

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                for (int i = 0; i < courseList.size(); i++) {
                    intent.putExtra("courseList" + String.valueOf(i), Parcels.wrap(courseList.get(i)));
                }
                intent.putExtra("listSize", courseList.size());
                startActivity(intent);
            }
        });

    }

    private void createCourses() {
        courseList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(ALL_COURSE_IDS, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONArray courses = json.jsonArray;
                try {
                    courseList = Courses.fromJsonArray(courses);
                    Log.i(TAG, "Courses: " + courseList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}