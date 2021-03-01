package com.example.antssignments.Fragments;

import android.content.Intent;
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

import com.example.antssignments.AssignmentAdapter;
import com.example.antssignments.ClassesAdapter;
import com.example.antssignments.MainActivity;
import com.example.antssignments.Models.Courses;
import com.example.antssignments.R;

import java.util.ArrayList;
import java.util.List;

public class ClassesFragment extends Fragment {

    public static final String TAG = "ClassesFragment";
    protected ClassesAdapter classesAdapter;
    private RecyclerView rvClasses;
    protected ArrayList<Courses> courseList;


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

        Bundle extras = this.getArguments();//getActivity().getIntent().getExtras();
        ArrayList<Courses> courseList  = extras.getParcelableArrayList("courseList");
        Log.i(TAG, "Courses: " + courseList.toString());
        rvClasses = view.findViewById(R.id.rvCourses);

        ClassesAdapter.OnClickListener onClickListener = new ClassesAdapter.OnClickListener() {
            @Override
            public void OnItemClicked(int position) {
                Toast.makeText(getContext(), "Single click at position " + position, Toast.LENGTH_SHORT).show();
            }
        };

        classesAdapter = new ClassesAdapter(getContext(), courseList, onClickListener);
        rvClasses.setAdapter(classesAdapter);
        rvClasses.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
