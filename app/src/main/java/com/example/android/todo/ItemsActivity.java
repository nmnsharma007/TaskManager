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
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private static final String LOG_TAG = ItemsActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
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
                intent.putExtra(getString(R.string.package_name)+"id",-1);
                startActivity(intent);
            }
        });
        // lookup the recycler view in activity layout
        mRecyclerView = findViewById(R.id.rvTasks);
        // associate the view model with the activity
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, tasks -> setUpRecyclerView(tasks));
    }

    private void setUpRecyclerView(List<Task> tasks) {
        final TaskAdapter taskAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(taskAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}