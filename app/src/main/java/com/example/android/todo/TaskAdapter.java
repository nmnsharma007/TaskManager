package com.example.android.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// create a basic adapter extending from Recycler View.Adapter
// we specify the custom ViewHolder which gives access to our views
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    // store a member variable for the contacts
    private List<Task> mTasks;
    // Pass in the tasks list into the constructor
    public TaskAdapter(List<Task> tasks){
        mTasks = tasks;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Holder contains a member variable
        // for any view that will be set
        public TextView titleTextView;
        public TextView statusTextView;
        // Create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder (View view){
            // stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance
            super(view);
            titleTextView = view.findViewById(R.id.task_title);
            statusTextView = view.findViewById(R.id.task_status);
        }
    }

    // Usually involves inflating a layout from XML and returning a holder
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //inflate the custom layout
        View taskView = inflater.inflate(R.layout.item_task,parent,false);
        // return a new view holder instance
        ViewHolder viewHolder = new ViewHolder(taskView);
        return viewHolder;
    }

    // involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {
        // get the data model based on position
        Task task = mTasks.get(position);
        // set item views based on views and data model
        TextView tvTitle = holder.titleTextView;
        TextView tvStatus = holder.statusTextView;
        tvTitle.setText(task.getTitle());
        tvStatus.setText((task.getStatus() ? "Completed" : "Incomplete"));
    }

    // return the total count of items in the list
    @Override
    public int getItemCount() {
        return mTasks.size();
    }
}
