package com.example.android.todo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The following code defines a task entity
 * Each instance of the item task represents a row in the task table
 */

@Entity(tableName = "tasks")
public class Task {
    // id which is a primary key
    @PrimaryKey(autoGenerate = true)
    private int id;

    // title of a task item
    @NonNull @ColumnInfo(name = "title")
    private String title;

    // status of task
    @NonNull @ColumnInfo(name = "status")
    private boolean status;

    public String getTitle() {
        return title;
    }

    public boolean getStatus() {
        return status;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setDescription(@NonNull boolean status){
        this.status = status;
    }

}
