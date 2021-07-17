package com.example.android.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemsActivity extends AppCompatActivity {

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
    }
}