package com.example.android.todo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mTaskRepository;

    private final LiveData<List<Task>> mAllTasks;

    public TaskViewModel(Application application) {
        super(application);
        mTaskRepository = new TaskRepository(application);
        mAllTasks = mTaskRepository.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }
}
