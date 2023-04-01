package com.example.proj_checkbox.Model;

public class ToDoModel
{
    private int id, status;
    private String task;

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getTask() {
        return task;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
