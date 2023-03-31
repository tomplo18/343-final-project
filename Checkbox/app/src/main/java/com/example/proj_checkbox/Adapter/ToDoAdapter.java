package com.example.proj_checkbox.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proj_checkbox.AddNewTask;
import com.example.proj_checkbox.MainActivity;
import com.example.proj_checkbox.Model.ToDoModel;
import com.example.proj_checkbox.R;
import com.example.proj_checkbox.Utils.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>
{
    private List<ToDoModel> toDoList;
    private MainActivity activity;
    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, MainActivity activity)
    {
        this.db = db;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position)
    {
        db.openDatabase();
        ToDoModel item = toDoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    db.updateStatus(item.getId(), 1);
                }
                else
                {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public int getItemCount()
    {
        return toDoList.size();
    }

    private boolean toBoolean(int n)
    {
        return n != 0;
    }

    public void setTasks(List<ToDoModel> toDoList)
    {
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

    public Context getContext()
    {
        return activity;
    }

    public  void deleteItem(int position)
    {
        ToDoModel item = toDoList.get(position);
        db.deleteTask(item.getId());
        toDoList.remove(position);
        notifyItemRemoved(position);
    }

    public  void  editItem(int position)
    {
        ToDoModel item = toDoList.get(position);
        Bundle bundle =  new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder
    {
        CheckBox task;

        ViewHolder(View view)
        {
            super(view);
            task = view.findViewById(R.id.taskBox);
        }
    }
}
