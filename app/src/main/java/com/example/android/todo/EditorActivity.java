package com.example.android.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class EditorActivity extends AppCompatActivity {

    private static final String LOG_TAG = EditorActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private int mId;
    private TaskRepository mTaskRepository;
    private EditText mEditTitle;
    private CheckBox mCheckStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        // Find the toolbar view inside the activity layout
        mToolbar = findViewById(R.id.my_toolbar);
        // find the edit text and the check box
        mEditTitle = findViewById(R.id.editTaskTitle);
        mCheckStatus = findViewById(R.id.checkBox);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTaskRepository = new TaskRepository(getApplication());
        mId = getIntent().getIntExtra(getString(R.string.package_name)+"id",-1);
        if(mId != -1){
            Handler handler = new Handler();
            final Thread t = new Thread() {
                @Override
                public void run() {
                    Task task = mTaskRepository.getTaskInfo(mId);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mEditTitle.setText(task.getTitle(), TextView.BufferType.EDITABLE);
                            mCheckStatus.setChecked(task.getStatus());
                        }
                    });
                }
            };
            t.start();
        }
        else{
            mEditTitle.setText("", TextView.BufferType.EDITABLE);
            mCheckStatus.setChecked(false);
        }
        // setup a click listener for back button on toolbar and resume the main activity
        mToolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(EditorActivity.this, ItemsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save:
                saveTask();
                return true;
            case R.id.delete:
                deleteTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // method to save the task
    public void saveTask() {
        if(mEditTitle.getText().toString().isEmpty()){
            Toast.makeText(this,"Title field cannot be null",Toast.LENGTH_SHORT).show();
        }
        else{
            Task task = new Task(mEditTitle.getText().toString(),mCheckStatus.isChecked());
            if(mId < 0){
                mTaskRepository.insert(task);
            }
            else{
                task.setId(mId);
                mTaskRepository.update(task);
            }
            Toast.makeText(this,"Task saved successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditorActivity.this,ItemsActivity.class);
            startActivity(intent);
        }
    }

    // method to delete the task
    public void deleteTask() {
        if(mId < 0){
            Toast.makeText(this,"No such task exists",Toast.LENGTH_SHORT).show();
        }
        else{
            Task task = new Task(mEditTitle.getText().toString(),mCheckStatus.isChecked());
            task.setId(mId);
            mTaskRepository.delete(task);
            Toast.makeText(this,"Task deleted successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditorActivity.this,ItemsActivity.class);
            startActivity(intent);
        }
    }
}