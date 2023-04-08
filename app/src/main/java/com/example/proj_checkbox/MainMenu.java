package com.example.proj_checkbox;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    // define the global variable
    TextView question1;
    // Add button Move to Activity
    Button next_Activity_button;
    Button calendar_Activity_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        next_Activity_button = (Button) findViewById(R.id.first_activity_button);

        // to checklist
        next_Activity_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
        });

        calendar_Activity_button = (Button) findViewById(R.id.calendar_activity_button);

        // to checklist
        calendar_Activity_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, CalendarMain.class);
            startActivity(intent);
        });
    }
}