package com.example.android.todo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;

    TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.loadAllTasks();
    }

    // Room executes all queries on a separate thread
    // Observed LiveData will notify the observer when the data has changed
    LiveData<List<Task>> getAllTasks(){
        return mAllTasks;
    }

    void insert(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.insertTask(task));
    }

    void update(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.updateTask(task));
    }

    void delete(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(()-> mTaskDao.deleteTask(task));
    }
}
