package com.example.antssignments.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

import java.io.File;
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("errorCheck","YES");
                    // We create the Courses after the permissions are allowed/accepted
                    createCourses();
                } else {
                    Log.i("errorCheck","NO");
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        courseList = new ArrayList<>();
        rvClasses = view.findViewById(R.id.rvCourses);

        //Request Read and Write Permissions
        requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE} ,1);

    }

    private void createCourseDirs() {
        File f;
        String legalPath;
        for(int i = 0; i < courseList.size(); i++){
            // courseList contains instances of courses, each course has a name, might have an illegal
            // characters such as ':' so that when creating a directory there won't be an error
            legalPath = getContext().getExternalFilesDir(null).getAbsolutePath() + "/" +
                    courseList.get(i).getCourseName().replace(":","");
            Log.i(TAG, "legalPath: " + legalPath);
            f = new File(legalPath);
            if(f.exists()){
                Log.i(TAG, "file exists");
            }else{
                if(f.mkdirs()){
                    Log.i(TAG, "folder created");
                }else{
                    Log.i(TAG, "folder NOT created");
                }
            }
            if(f.isDirectory()){
                Log.i(TAG, "file is a directory");
            }
        }
    }


    private void createCourses() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(ALL_COURSE_IDS, ACCESS_TOKEN), new JsonHttpResponseHandler() {
            NoteFragment nextFrag= new NoteFragment();
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONArray courses = json.jsonArray;
                try {
                    courseList = Course.fromJsonArray(courses);
                    Log.i(TAG, "Courses: " + courseList.toString());
                    createCourseDirs();
                    ClassesAdapter.OnClickListener onClickListener = new ClassesAdapter.OnClickListener() {
                        @Override
                        public void OnItemClicked(String position) {
                            //Toast.makeText(getContext(), "Class name: " + position, Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("course_name", position);
                            nextFrag.setArguments(bundle);
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