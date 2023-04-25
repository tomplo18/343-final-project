package com.example.proj_checkbox;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET;
    private TextView eventDate,eventTime;
    private LocalTime time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDate.setText("Date: "+CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTime.setText("Date: "+CalendarUtils.formattedTime(time));
    }

    private void initWidgets() {
        eventDate=findViewById(R.id.eventDate);
        eventNameET=findViewById(R.id.eventNameET);
        eventTime=findViewById(R.id.eventTime);
    }

    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        Event newEvent=new Event(eventName,CalendarUtils.selectedDate,time);
        Event.eventsList.add(newEvent);
        finish();
    }
}
