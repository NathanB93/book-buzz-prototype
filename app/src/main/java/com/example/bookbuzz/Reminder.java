package com.example.bookbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Reminder extends AppCompatActivity {

    private Button setButton;
    private EditText reminderText;
    private DatePicker reminderTime;
    private int reminderId = 0;
    private Button backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        backHomeButton = findViewById(R.id.homeBackButton2);
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToHomePageIntent = new Intent(Reminder.this, MainActivity.class);
                startActivity(backToHomePageIntent);
            }
        });


        setButton = findViewById(R.id.setButton);//Initialize id of set button
        reminderText = findViewById(R.id.reminderText);//Initialize id of reminder text button
        reminderTime = findViewById(R.id.reminderTime);//Initialize id of reminder time button
        actions();
    }

    private void actions() {
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reminderIntent = new Intent(Reminder.this, AlertReceiver.class);
                reminderIntent.putExtra("ReminderId", reminderId);
                reminderIntent.putExtra("ReminderText", reminderText.getText().toString());

                PendingIntent alermIntent = PendingIntent.getBroadcast(Reminder.this,
                        0, reminderIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.YEAR, reminderTime.getYear());
                startTime.set(Calendar.MONDAY, reminderTime.getMonth());
                startTime.set(Calendar.DAY_OF_MONTH, reminderTime.getDayOfMonth());
                long reminderStartTime = startTime.getTimeInMillis();

                alarmManager.set(AlarmManager.RTC_WAKEUP, reminderStartTime, alermIntent);

                Toast.makeText(Reminder.this, "Reminder has been Setup!!!!",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}