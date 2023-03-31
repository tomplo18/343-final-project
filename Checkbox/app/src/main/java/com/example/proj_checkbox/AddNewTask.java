package com.example.proj_checkbox;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.proj_checkbox.Model.ToDoModel;
import com.example.proj_checkbox.Utils.DatabaseHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment
{
    public static final String TAG = "ActionBottomDialog";

    private EditText newTaskText;
    private Button newTaskSaveButton;
    private DatabaseHandler db;

    public static  AddNewTask newInstance()
    {
        return new AddNewTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.new_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void  onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        newTaskText = getView().findViewById(R.id.newTaskText);
        newTaskSaveButton = getView().findViewById(R.id.newTaskButton);

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if(bundle != null)
        {
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task);
            if(task.length() > 0)
            {
                int a = 0;
                //for making button filled in if can be saved
            }
        }
        newTaskText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().equals(""))
                {
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.GRAY);
                }
                else
                {
                    newTaskSaveButton.setEnabled(true);
                    newTaskSaveButton.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });

        boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String txt = newTaskText.getText().toString();
                if(finalIsUpdate)
                {
                    db.updateTask(bundle.getInt("id"), txt);
                }
                else
                {
                    ToDoModel task = new ToDoModel();
                    task.setTask(txt);
                    task.setStatus(0);
                    db.insertTask(task);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface)
    {
        Activity activity = getActivity();
        if(activity instanceof  DialogCloseListener)
        {
            ((DialogCloseListener)activity).handleDialogClose(dialogInterface);
        }
    }
}
