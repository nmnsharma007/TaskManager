package com.example.android.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private static final String LOG_TAG = ItemsActivity.class.getSimpleName();
    // Recycler view for the main activity
    RecyclerView recyclerView;
    // member variable for the View Model
    private TaskViewModel mTaskViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        // floating action bar button
        FloatingActionButton fab = findViewById(R.id.add_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open up an intent to go to the editor activity when the fab button is pressed
                Intent intent = new Intent(ItemsActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        // lookup the recycler view in activity layout
        recyclerView = findViewById(R.id.rvTasks);
        // associate the view model with the activity
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, tasks -> setUpRecyclerView(tasks));
    }

    private void setUpRecyclerView(List<Task> tasks) {
        final TaskAdapter taskAdapter = new TaskAdapter(null);
        recyclerView.setAdapter(taskAdapter);
        if(recyclerView == null) {
            Log.v(LOG_TAG,"yes it is null");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}