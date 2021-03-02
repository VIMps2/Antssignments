package com.example.antssignments.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.antssignments.ClassesAdapter;
import com.example.antssignments.Models.Course;
import com.example.antssignments.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import okhttp3.Headers;

public class ClassesFragment extends Fragment {

    public static final String TAG = "ClassesFragment";
    public static final String ACCESS_TOKEN = "4407~UeskhdnHkzhYvPj5UxZFwJFTDhZcJJwaf98sJRP4loywfWHYvldN4HFPmxLOAuUV";
    public static final String ALL_COURSE_IDS = "https://canvas.eee.uci.edu/api/v1/courses?access_token=%s";

    private ClassesAdapter classesAdapter;
    private RecyclerView rvClasses;
    private ArrayList<Course> courseList;


    public ClassesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        courseList = new ArrayList<>();
        rvClasses = view.findViewById(R.id.rvCourses);
        createCourses();


    }

    private void createCourses() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(ALL_COURSE_IDS, ACCESS_TOKEN), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONArray courses = json.jsonArray;
                try {
                    courseList = Course.fromJsonArray(courses);
                    Log.i(TAG, "Courses: " + courseList.toString());
                    ClassesAdapter.OnClickListener onClickListener = new ClassesAdapter.OnClickListener() {
                        @Override
                        public void OnItemClicked(int position) {
                            Toast.makeText(getContext(), "Single click at position " + position, Toast.LENGTH_SHORT).show();
                            NoteFragment nextFrag= new NoteFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.flContainer , nextFrag, "findThisFragment")
                                    .addToBackStack(null)
                                    .commit();

                        }
                    };

                    classesAdapter = new ClassesAdapter(getContext(), courseList, onClickListener);
                    rvClasses.setAdapter(classesAdapter);
                    rvClasses.setLayoutManager(new LinearLayoutManager(getContext()));
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