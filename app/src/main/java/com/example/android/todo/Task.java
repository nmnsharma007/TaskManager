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
    @PrimaryKey(autoGenerate = true) @ColumnInfo (name = "id")
    private int id;

    // title of a task item
    @NonNull @ColumnInfo(name = "title")
    private String title;

    // status of task
    @NonNull @ColumnInfo(name = "status")
    private boolean status;

    public Task(@NonNull String title,@NonNull boolean status){
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public boolean getStatus() {
        return status;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setStatus(@NonNull boolean status){
        this.status = status;
    }

}
