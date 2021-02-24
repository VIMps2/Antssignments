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
import android.widget.Toast;

import com.example.antssignments.Fragments.AssignmentFragment;
import com.example.antssignments.Fragments.ClassesFragment;
import com.example.antssignments.Models.Assignments;
import com.example.antssignments.Models.Courses;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "AssignmentsActivity";

        final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    List<Courses> courseList;
    List<Assignments> assignmentsList;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case (R.id.action_assignments):
                        Toast.makeText(MainActivity.this, "Assignments", Toast.LENGTH_SHORT).show();
                        fragment = new AssignmentFragment();
                        break;
                    case (R.id.action_classes):
                        Toast.makeText(MainActivity.this, "Classes", Toast.LENGTH_SHORT).show();
                        fragment = new ClassesFragment();
                        break;
                    default:
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return false;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.action_assignments);

        AssignmentFragment assignmentFragment = new AssignmentFragment();
        courseList = new ArrayList<>();


        int courseLength = getIntent().getIntExtra("listSize", 0);
        for (int i = 0; i < courseLength; i++) {
            Courses course = (Courses) Parcels.unwrap(getIntent().getParcelableExtra("courseList" + String.valueOf(i)));
            courseList.add(course);

        }
        Log.i(TAG, "Courses: " + courseList.toString());

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("courseList", (ArrayList<? extends Parcelable>) courseList);
        assignmentFragment.setArguments(bundle);


    }






}
