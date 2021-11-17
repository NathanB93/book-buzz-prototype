package com.example.bookbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadHistory extends AppCompatActivity {

    private Button viewLibrary;
    private Button setReminder;
    private Button goHome;
    private Button viewNotes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_history);

        viewLibrary = findViewById(R.id.view_library_rh);
        setReminder = findViewById(R.id.set_reminder_rh);
        goHome = findViewById(R.id.home__rh);
        viewNotes = findViewById(R.id.view_notes_rh);


        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNotes();
            }
        });

        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReminders();
            }
        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

        viewLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLibrary();
            }
        });





    }

    public void goToNotes(){
        String isbn = DataUtility.getCurrentBook().getIsbn();
        Intent viewNotePageIntent = new Intent(this, ViewNotes.class);
        viewNotePageIntent.putExtra("isbn", isbn);
        startActivity(viewNotePageIntent);
    }

    public void viewLibrary() {
        Intent intent = new Intent(this, ViewLibrary.class);
        startActivity(intent);
    }

    public void goToReminders() {
        Intent intent = new Intent(this, Reminder.class);
        startActivity(intent);
    }


    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}