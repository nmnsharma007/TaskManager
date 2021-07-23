package com.example.android.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class EditorActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        // Find the toolbar view inside the activity layout
        mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
    }
}