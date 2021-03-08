package com.example.antssignments.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antssignments.NoteAdapter;
import com.example.antssignments.R;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class NoteFragment extends Fragment {
    public static final String TAG = "NoteFragment";
    private String courseName;
    private String textPath;

    ArrayList<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    NoteAdapter noteAdapter;

    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = this.getArguments();
        courseName = extras.getString("course_name");
//        Toast.makeText(getContext(), "Class name: " + courseName, Toast.LENGTH_SHORT).show();
        getNotes();
    }

    // given a course name, this function will find the directory of that course and
    // open all the notes file
    private void getNotes() {
        textPath = getContext().getExternalFilesDir(null).getPath() + "/" +
                courseName.replace(":", "") + ".txt";
        File f = new File(textPath);

        try {
            items = (ArrayList<String>) org.apache.commons.io.FileUtils.readLines(f,
                    Charset.defaultCharset());
        }catch (IOException e){
            Log.e(TAG, "Error reading items");
            items = new ArrayList<>();
        }
    }

    private void saveNotes(){
        try {
            org.apache.commons.io.FileUtils.writeLines(new File(textPath), items);
        } catch (IOException e) {
            Log.e(TAG, "Error writing items", e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvItems = view.findViewById(R.id.rvItems);
        btnAdd = view.findViewById(R.id.btnAdd);
        etItem = view.findViewById(R.id.etItem);

        // Get prefixes for each courseName

        noteAdapter = new NoteAdapter(getContext(), items);
        rvItems.setAdapter(noteAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
//        etItem.setText(courseName + ": ");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                items.add(todoItem);
                noteAdapter.notifyItemInserted(items.size() - 1);
                etItem.setText("");
                Toast.makeText(getContext(), "Item was added", Toast.LENGTH_LONG).show();
                saveNotes();
            }
        });
    }
}