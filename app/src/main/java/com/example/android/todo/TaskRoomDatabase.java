package com.example.android.todo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// database class to return single instance of the Room database
@Database(entities = {Task.class},version = 1,exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    // declare the singleton instance of the class as volatile
    private static volatile TaskRoomDatabase INSTANCE;

    // using multithreading to fetch the database so that the UI doesn't freeze
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TaskRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null){
            // run database operations on a background thread
            synchronized (TaskRoomDatabase.class) {
                if(INSTANCE == null){
                    // create a singleton database instance to be used globally
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class,"task_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                TaskDao taskDao = INSTANCE.taskDao();
                taskDao.deleteAll();
                Task task = new Task("Enjoy Life",false);
                taskDao.insertTask(task);
            });
        }
    };
}
