package com.example.antssignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;

import com.example.antssignments.Fragments.AssignmentFragment;
import com.example.antssignments.Fragments.ClassesFragment;
import com.example.antssignments.Models.Course;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    ArrayList<Course> courseList;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseList = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        int courseLength = getIntent().getIntExtra("listSize", 0);
        for (int i = 0; i < courseLength; i++) {
            Course course = (Course) Parcels.unwrap(getIntent().getParcelableExtra("courseList" + String.valueOf(i)));
            courseList.add(course);
        }
        Log.i(TAG, "Courses: " + courseList.toString());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_classes:
                        fragment = new ClassesFragment();
                        break;
                    case R.id.action_assignments:
                    default:
                        fragment = new AssignmentFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_assignments);


    }

}
