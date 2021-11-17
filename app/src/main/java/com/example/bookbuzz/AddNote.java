package com.example.bookbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

/***
 * This class is used to add note of book.
 */
public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        TextView bookTitle = findViewById(R.id.book_title_add_note);
        String isbn = getIntent().getStringExtra("isbn");

        bookTitle.setText(DataUtility.getABookByIsbn(isbn).getCurrentBookName());
        initializedListner();

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePageIntent = new Intent(AddNote.this, MainActivity.class);
                startActivity(homePageIntent);
            }
        });

        Button viewLibrary = findViewById(R.id.myLibraryButton);
        viewLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewnoteIntent = new Intent(AddNote.this, ViewLibrary.class);
                startActivity(viewnoteIntent);
            }
        });

        Button viewNote = findViewById(R.id.viewNoteButton2);
        viewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isbn = getIntent().getStringExtra("isbn");

                Intent viewnoteIntent = new Intent(AddNote.this, ViewNotes.class);
                viewnoteIntent.putExtra("isbn", isbn);
                startActivity(viewnoteIntent);
            }
        });


    }

    private void initializedListner() {
        Button saveButton = findViewById(R.id.save_note_button);
        Button clearButton = findViewById(R.id.clear_button);
        EditText enterText = findViewById(R.id.note_entry_text);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isbn = getIntent().getStringExtra("isbn");
                Log.i("on save", "clicking note save");
                String userNote = enterText.getText().toString();
                NoteModel currentNote = new NoteModel(currentDate, userNote);
                BookBuzzDataModel book = DataUtility.getABookByIsbn(isbn);
                book.getNotes().add(currentNote);
                Toast.makeText(AddNote.this, "Note has been added successfully!!!" + "\n",
                        Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterText.setText("");
                Toast.makeText(AddNote.this, "Note has been cleared successfully!!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}