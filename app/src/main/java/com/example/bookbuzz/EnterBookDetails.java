package com.example.bookbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.makeText;

public class EnterBookDetails extends AppCompatActivity {

    EditText enter_author_name;
    EditText enter_book_name;
    EditText page_number_entry;
    EditText isbn_entry;
    EditText genre_entry;
    EditText year_entry;
    Button addBook;
    Button home;
    Button viewLibrary;
    Button setReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_book_details);

        enter_author_name = findViewById(R.id.enter_author_name);
        enter_book_name = findViewById(R.id.enter_book_name);
        page_number_entry = findViewById(R.id.page_number_entry);
        isbn_entry = findViewById(R.id.isbn_entry);
        year_entry = findViewById(R.id.year_entry);
        genre_entry = findViewById(R.id.genre_entry);
        addBook = findViewById(R.id.addBookButton);
        home = findViewById(R.id.home_ed);
        viewLibrary = findViewById(R.id.view_library_ed);
        setReminder = findViewById(R.id.set_reminder_ed);



        if(getIntent().hasExtra("edit")){

            String isbn = getIntent().getStringExtra("isbn");
            BookBuzzDataModel book = DataUtility.getABookByIsbn(isbn);

            enter_author_name.setText(book.getAuthor());
            enter_book_name.setText(book.getCurrentBookName());
            page_number_entry.setText(book.getPages());
            isbn_entry.setText(book.getIsbn());
            year_entry.setText(book.getYear());
            genre_entry.setText(book.getGenre());

        } else if (getIntent().hasExtra("db")){
            String isbn = getIntent().getStringExtra("isbn");
            BookBuzzDataModel book = DataUtility.getBookByIsbnDB(isbn);

            enter_author_name.setText(book.getAuthor());
            enter_book_name.setText(book.getCurrentBookName());
            page_number_entry.setText(book.getPages());
            isbn_entry.setText(book.getIsbn());
            year_entry.setText(book.getYear());
            genre_entry.setText(book.getGenre());

        }



        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
        viewLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewLibrary();
            }
        });
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetReminder();
            }
        });

    }

    public void addBook() {


        if(enter_book_name.getText().toString().length() == 0) {
            makeText(this, "Title field must not be left blank!", Toast.LENGTH_SHORT).show();
        } else if(enter_author_name.getText().toString().length() == 0) {
            makeText(this, "Author field must not be left blank!", Toast.LENGTH_SHORT).show();
        } else if(isbn_entry.getText().toString().length() == 0) {
            makeText(this, "ISBN field must not be left blank!", Toast.LENGTH_SHORT).show();
        } else if (page_number_entry.getText().toString().length() == 0) {
            makeText(this, "Pages field must not be left blank!", Toast.LENGTH_SHORT).show();
        } else {

            if (getIntent().hasExtra("edit")) {

                String isbn = getIntent().getStringExtra("isbn");
                BookBuzzDataModel book = DataUtility.getABookByIsbn(isbn);

                book.setCurrentBookName(enter_book_name.getText().toString());
                book.setIsbn(isbn_entry.getText().toString());
                book.setAuthor(enter_author_name.getText().toString());

                book.setPages(page_number_entry.getText().toString());


                if(year_entry.getText().toString().length() == 0) {
                    book.setYear("N/A");
                } else {
                    book.setYear(year_entry.getText().toString());
                }

                if(year_entry.getText().toString().length() == 0) {
                    book.setGenre("N/A");
                } else {
                    book.setGenre(genre_entry.getText().toString());
                }



            } else if (getIntent().hasExtra("db")) {

                String isbn = getIntent().getStringExtra("isbn");
                BookBuzzDataModel book = DataUtility.getBookByIsbnDB(isbn);
                int coverPic = book.getImage();

                BookBuzzDataModel addedBook = new BookBuzzDataModel((enter_book_name.getText().toString()), isbn_entry.getText().toString());
                addedBook.setAuthor(enter_author_name.getText().toString());
                addedBook.setGenre(genre_entry.getText().toString());
                addedBook.setPages(page_number_entry.getText().toString());
                addedBook.setYear(year_entry.getText().toString());
                addedBook.setImage(coverPic);
                DataUtility.addABook(addedBook);

            } else {
                BookBuzzDataModel addedBook = new BookBuzzDataModel((enter_book_name.getText().toString()), isbn_entry.getText().toString());
                addedBook.setAuthor(enter_author_name.getText().toString());
                addedBook.setPages(page_number_entry.getText().toString());
                addedBook.setImage(R.drawable.default_cover_art);

                if(year_entry.getText().toString().length() == 0) {
                    addedBook.setYear("N/A");
                } else {
                    addedBook.setYear(year_entry.getText().toString());
                }

                if(year_entry.getText().toString().length() == 0) {
                    addedBook.setGenre("N/A");
                } else {
                    addedBook.setGenre(genre_entry.getText().toString());
                }

                DataUtility.addABook(addedBook);

            }
            openSetStatus();
        }

    }

    public void openSetStatus() {

        String isbn = isbn_entry.getText().toString();
        Intent intent = new Intent(this, SetStatus.class);
        intent.putExtra("isbn", isbn);
        startActivity(intent);
    }

    public void openViewLibrary() {
        Intent intent = new Intent(this, ViewLibrary.class);
        startActivity(intent);
    }
    public void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openSetReminder() {
        Intent intent = new Intent(this, Reminder.class);
        startActivity(intent);
    }


}

