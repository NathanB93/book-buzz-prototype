package com.example.bookbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewBookDatabase extends AppCompatActivity {

    ImageView bookPicture;
    TextView title;
    TextView genre;
    TextView author;
    TextView published;
    TextView pages;
    TextView bookISBN;
    Button addToLibrary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book_database);


        bookPicture = findViewById(R.id.bookPictureDB);
        title = findViewById(R.id.title_txt_vb_db);
        genre = findViewById(R.id.genre_txt_vb_db);
        author = findViewById(R.id.author_txt_vb_db);
        published = findViewById(R.id.year_txt_vb_db);
        pages = findViewById(R.id.pages_txt_vb_db);
        bookISBN = findViewById(R.id.isbn_txt_vb_db);
        setBookDetails();


    }

    private void setBookDetails() {

        String isbn = getIntent().getStringExtra("isbn");
        BookBuzzDataModel book = DataUtility.getBookByIsbnDB(isbn);

        title.setText(book.getCurrentBookName());
        bookPicture.setImageResource(book.getImage());
        pages.setText(book.getPages());
        published.setText(book.getYear());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        bookISBN.setText(book.getIsbn());
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