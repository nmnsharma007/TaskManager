package com.example.android.todo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The following code defines a task entity
 * Each instance of the item task represents a row in the task table
 */

@Entity
public class Task {
    // id which is a primary key
    @PrimaryKey
    private int id;

    // title of a task item
    @NonNull @ColumnInfo
    private String title;

    // status of task
    @NonNull @ColumnInfo
    private String status;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public void setTodoId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String status){
        this.status = status;
    }

}
