package com.example.bookbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewLibrary extends AppCompatActivity {

    RecyclerView libraryRecyclerView;

    ViewLibraryAdapter viewLibraryAdapter;
    ArrayList<BookBuzzDataModel> library;

    Button readHistory;
    Button setReminder;
    Button viewNotes;
    Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_library);

        library = DataUtility.getBooks();
        libraryRecyclerView = findViewById(R.id.searchDatabaserecyclerView);

        readHistory = findViewById(R.id.view_read_history_vl);
        setReminder = findViewById(R.id.set_reminder_vl);
        viewNotes = findViewById(R.id.view_notes_vl);
        home = findViewById(R.id.home__vl);

        viewLibraryAdapter = new ViewLibraryAdapter(this, library);
        libraryRecyclerView.setAdapter(viewLibraryAdapter);
        libraryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().hasExtra("cb")) {
            viewLibraryAdapter.setCB(true);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewNotes();
            }
        });
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetReminder();
            }
        });
        readHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReadHistory();
            }
        });






    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_library_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.library_action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                viewLibraryAdapter.getFilter().filter(searchText);
                return false;
            }
        });
        return true;

    }


    public void openViewNotes(){
        String isbn = DataUtility.getCurrentBook().getIsbn();
        Intent viewNotePageIntent = new Intent(this, ViewNotes.class);
        viewNotePageIntent.putExtra("isbn", isbn);
        startActivity(viewNotePageIntent);
    }

    public void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openSetReminder() {
        Intent intent = new Intent(this, Reminder.class);
        startActivity(intent);
    }

    public void openReadHistory(){
        Intent intent = new Intent(this, ReadHistory.class);
        startActivity(intent);
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.library_by_author:
                viewLibraryAdapter.setCriteria("author");
                return true;
            case R.id.library_by_title:
                viewLibraryAdapter.setCriteria("title");
                return true;
            case R.id.library_by_genre:
                viewLibraryAdapter.setCriteria("genre");
                return true;
            case R.id.library_by_year:
                viewLibraryAdapter.setCriteria("year");
                return true;
            case R.id.library_by_status:
                viewLibraryAdapter.setCriteria("status");
                return true;
            case R.id.library_by_isbn:
                viewLibraryAdapter.setCriteria("isbn");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}