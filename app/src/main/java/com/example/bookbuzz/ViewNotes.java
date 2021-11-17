package com.example.bookbuzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewNotes extends AppCompatActivity {
    Button homeButton;
    Button viewLibrary;
    Button addNote;
    Button readHistory;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        String isbn = getIntent().getStringExtra("isbn");
        TextView bookName = findViewById(R.id.textViewBookName);
        bookName.setText(DataUtility.getCurrentBook().getCurrentBookName());

        List<NoteModel> notes = DataUtility.getABookByIsbn(isbn).getNotes();
        NoteViewAdaptor noteViewAdaptor = new NoteViewAdaptor(this, notes);

        recyclerView = findViewById(R.id.noteRecycleView);
        recyclerView.setAdapter(noteViewAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteViewAdaptor.notifyDataSetChanged();

        homeButton = findViewById(R.id.homeFromViewNoteButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeButtonIntent = new Intent(ViewNotes.this, MainActivity.class);
                startActivity(homeButtonIntent);
            }
        });

        viewLibrary = findViewById(R.id.myLibraryFromNotebutton2);

        viewLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewLibrary();
            }

                });



        readHistory = findViewById(R.id.view_read_history_button);

        readHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {readHistory();}
        });

        addNote = findViewById(R.id.addNoteFromViewNoteButton3);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isbn = getIntent().getStringExtra("isbn");
                Intent addNoteButtonIntent = new Intent(ViewNotes.this, AddNote.class);
                addNoteButtonIntent.putExtra("isbn", isbn);
                startActivity(addNoteButtonIntent);
            }
        });


        }
    public void openViewLibrary() {
        Intent intent = new Intent(this, ViewLibrary.class);
        startActivity(intent);
    }

    public void readHistory(){
        Intent intent = new Intent(this, ReadHistory.class);
        startActivity(intent);
    }




}