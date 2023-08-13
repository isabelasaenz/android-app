package com.example.todoapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.AddNewTask;
import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.R;
import com.example.todoapp.Utils.DatabaseHelper;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDoModel> mList;
    private final MainActivity activity;
    private final DatabaseHelper myDatabase;

    // Constructor to initialize the adapter with the database and activity
    public ToDoAdapter(DatabaseHelper myDatabase, MainActivity activity) {
        this.activity = activity;
        this.myDatabase = myDatabase;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the task layout and create a new view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModel item = mList.get(position);
        // Set the task text and checkbox status for each item
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBoolean(item.getStatus()));
        // Set a listener for checkbox changes
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the task status in the database based on checkbox state
                if (isChecked) {
                    myDatabase.updateStatus(item.getId(), 1);
                } else {
                    myDatabase.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    // Utility function to convert integer to boolean
    public boolean toBoolean(int num) {
        return num != 0;
    }

    // Get the context of the associated activity
    public Context getContext() {
        return activity;
    }

    // Update the list of tasks and notify data changes
    public void setTasks(List<ToDoModel> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    // Delete a task from the list and notify the removal
    public void deleteTask(int position) {
        ToDoModel item = mList.get(position);
        myDatabase.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    // Edit a task using a dialog and show it
    public void editItem(int position) {
        ToDoModel item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // View holder class for each task item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox mCheckBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.mcheckbox);
        }
    }
}
