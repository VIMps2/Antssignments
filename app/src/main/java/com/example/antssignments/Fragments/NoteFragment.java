package com.example.antssignments.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antssignments.NoteAdapter;
import com.example.antssignments.R;

import java.util.ArrayList;
import java.util.List;


public class NoteFragment extends Fragment {
    private String courseName;

    ArrayList<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    NoteAdapter noteAdapter;

    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        items = new ArrayList<>();
        rvItems = view.findViewById(R.id.rvItems);
        btnAdd = view.findViewById(R.id.btnAdd);
        etItem = view.findViewById(R.id.etItem);

        // Get prefixes for each courseName
        Bundle extras = this.getArguments();
        courseName = extras.getString("course_name");
        Toast.makeText(getContext(), "Class name: " + courseName, Toast.LENGTH_SHORT).show();

        noteAdapter = new NoteAdapter(getContext(), items);
        rvItems.setAdapter(noteAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
        etItem.setText(courseName + ": ");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                items.add(todoItem);
                noteAdapter.notifyItemInserted(items.size() - 1);
                etItem.setText("");
                Toast.makeText(getContext(), "Item was added", Toast.LENGTH_LONG).show();
            }
        });
    }
}