package com.example.bookbuzz;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchDatabase extends AppCompatActivity {


    RecyclerView databaseRecyclerView;
    SearchDatabaseAdapter searchDatabaseAdapter;


    ArrayList<BookBuzzDataModel> database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_library);



        databaseRecyclerView = findViewById(R.id.searchDatabaserecyclerView);

        database = DataUtility.getBooksDB();


        searchDatabaseAdapter = new SearchDatabaseAdapter(this, database);
        databaseRecyclerView.setAdapter(searchDatabaseAdapter);
        databaseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_db_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                searchDatabaseAdapter.getFilter().filter(searchText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.by_author:
                searchDatabaseAdapter.setCriteria("author");
                return true;
            case R.id.by_title:
                searchDatabaseAdapter.setCriteria("title");
                return true;
            case R.id.by_genre:
                searchDatabaseAdapter.setCriteria("genre");
                return true;
            case R.id.by_year:
                searchDatabaseAdapter.setCriteria("year");
                return true;
            case R.id.by_isbn:
                searchDatabaseAdapter.setCriteria("isbn");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }




    }

}
