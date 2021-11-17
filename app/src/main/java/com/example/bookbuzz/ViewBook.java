package com.example.bookbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewBook extends AppCompatActivity {

    ImageView bookPicture;
    TextView title;
    Button removeBook;
    Button bookmark;
    TextView status;
    TextView author;
    TextView published;
    TextView pages;
    TextView bookISBN;
    EditText bookmarkPage;
    Button viewLibrary;
    Button editBook;
    Button home;
    Button setReminder;
    Button addNote;
    Button viewNotes;
    Button viewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        removeBook = findViewById(R.id.addToLibraryVBD);
        bookPicture = findViewById(R.id.bookPicture);
        title = findViewById(R.id.title_txt_vb);
        status = findViewById(R.id.status_txt_vb);
        author = findViewById(R.id.author_txt_vb);
        published = findViewById(R.id.year_txt_vb);
        pages = findViewById(R.id.pages_txt_vb);
        bookISBN = findViewById(R.id.isbn_txt_vb);
        home = findViewById(R.id.home__vb);
        setReminder = findViewById(R.id.set_reminder_vb);
        viewLibrary = findViewById(R.id.view_library_vb);
        bookmark = findViewById(R.id.bookmark_btn);
        bookmarkPage = findViewById(R.id.editBookmark);
        editBook = findViewById(R.id.editBook);
        viewNotes = findViewById(R.id.viewNotesVB);
        addNote = findViewById(R.id.addNoteVB);
        viewHistory = findViewById(R.id.view_read_history_button);
        setBookDetails();


        editBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBook();
            }
        });

        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBook();
            }
        });

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBookmark();
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
        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewNotes();
            }
        });
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNote();
            }

        });
        viewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {viewHistory();}
        });
    }

    private void setBookDetails() {

        String isbn = getIntent().getStringExtra("isbn");
        BookBuzzDataModel book = DataUtility.getABookByIsbn(isbn);

        title.setText(book.getCurrentBookName());
        bookPicture.setImageResource(book.getImage());
        status.setText(book.getStatus());
        bookmarkPage.setText(book.getBookmark());
        pages.setText(book.getPages());
        published.setText(book.getYear());
        author.setText(book.getAuthor());
        bookISBN.setText(book.getIsbn());
    }

    private void removeBook() {

        String isbn = getIntent().getStringExtra("isbn");
        DataUtility.removeBook(isbn);
        goBackToLibrary();
    }

    public void viewHistory() {
        Intent intent = new Intent(this, ReadHistory.class);
        startActivity(intent);
    }


    public void goBackToLibrary() {
        Intent intent = new Intent(this, ViewLibrary.class);
        startActivity(intent);
    }

    public void setBookmark(){
        String page = bookmarkPage.getText().toString();
        String isbn = getIntent().getStringExtra("isbn");
        BookBuzzDataModel book = DataUtility.getABookByIsbn(isbn);
        book.setBookmark(page);
        bookmarkPage.setText(book.getBookmark());
        goBackToLibrary();

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
    public void editBook(){

        String isbn = this.bookISBN.getText().toString();
        String edit = "edit";
        Intent intent = new Intent(this, EnterBookDetails.class);
        intent.putExtra("isbn", isbn);
        intent.putExtra("edit",edit);
        startActivity(intent);
    }
    public void openAddNote() {

        String isbn = this.bookISBN.getText().toString();
        Intent intent = new Intent(this, AddNote.class);
        intent.putExtra("isbn", isbn);
        startActivity(intent);

    }
    public void openViewNotes() {

        String isbn = this.bookISBN.getText().toString();
        Intent intent = new Intent(this, ViewNotes.class);
        intent.putExtra("isbn", isbn);
        startActivity(intent);
    }

}