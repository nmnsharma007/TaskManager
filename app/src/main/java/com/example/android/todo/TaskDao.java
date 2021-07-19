package com.example.android.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {
    /**
     *
      * @param task
     * insert the task into the table
     */
    @Insert
    public void insertTask(Task task);

    /**
     * delete the task from the table
     * @param task
     */
    @Delete
    public void deleteTask(Task task);

    /**
     * update the task in the table
     * @param task
     */
    @Update
    public void updateTask(Task task);

    /**
     * query the whole table an
     * @return
     */
    @Query(
            "SELECT * FROM tasks"
    )
    public LiveData<List<Task>> loadAllTasks();

    /**
     * Delete the whole table
     */
    @Query("DELETE FROM tasks")
    void deleteAll();
}
