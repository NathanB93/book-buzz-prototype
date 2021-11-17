package com.example.bookbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button search;
    private Button addNewBook;
    private Button addNote;
    private Button viewNotes;
    private Button setReminder;
    private Button view_library;
    private Button view_history;
    private ImageView currentBookImage;
    private Button setCurrentBook;
    private static Boolean firstOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        search = findViewById(R.id.search_btn);
        addNewBook = findViewById(R.id.add_new_book_button);
        addNote = findViewById(R.id.add_note_main);
        viewNotes = findViewById(R.id.view_notes_btn);

        setReminder = findViewById(R.id.set_reminder_button);
        view_library = findViewById(R.id.view_library_button);
        view_history = findViewById(R.id.view_read_history_button);
        currentBookImage = findViewById(R.id.current_book);
        setCurrentBook = findViewById(R.id.current_book_button);


        if(firstOpen) {

            createUserLibrary();

            createDatabase();

            BookBuzzDataModel lanark = new BookBuzzDataModel("Lanark", "ISBN-64321");
            lanark.setAuthor("Alasdair Gray");
            lanark.setImage(R.drawable.lanark_book);
            lanark.setPages("300");
            lanark.setYear("1981");
            lanark.setStatus("Reading");
            lanark.setBookmark("50");
            lanark.setGenre("Dystopian");

            DataUtility.addABook(lanark);
            DataUtility.setCurrentBook(lanark);
            setFirstOpen(false);

        }

        currentBookImage.setImageResource(DataUtility.getCurrentBook().getImage());






        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setReminderIntent = new Intent(MainActivity.this, Reminder.class);
                startActivity(setReminderIntent);
            }
        });
        currentBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToViewBook();
            }
        });
        setCurrentBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cb = "cb";
                Intent setCurrentBookIntent = new Intent(MainActivity.this, ViewLibrary.class);
                setCurrentBookIntent.putExtra("cb", cb);
                startActivity(setCurrentBookIntent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchPage();
            }
        });

        addNewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewBook();
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNote();
            }
        });

        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNotes();
            }
        });

        view_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLibrary();
            }
        });
        view_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {viewHistory();}
        });
    }

    public void goToSearchPage(){
        Intent searchPageIntent = new Intent(this, SearchDatabase.class);
        startActivity(searchPageIntent);
    }

    public void createNewBook(){
        Intent createBookIntent = new Intent(this, AddNewBook.class);
        startActivity(createBookIntent);
    }

    public void createNewNote(){
        String isbn = DataUtility.getCurrentBook().getIsbn();
        Intent createNoteIntent = new Intent(this, AddNote.class);
        createNoteIntent.putExtra("isbn", isbn);
        startActivity(createNoteIntent);
    }

    public void goToNotes(){
        String isbn = DataUtility.getCurrentBook().getIsbn();
        Intent viewNotePageIntent = new Intent(this, ViewNotes.class);
        viewNotePageIntent.putExtra("isbn", isbn);
        startActivity(viewNotePageIntent);
    }

    public void setFirstOpen(boolean first){
        this.firstOpen = first;
    }


    public void viewLibrary() {
        Intent intent = new Intent(this, ViewLibrary.class);
        startActivity(intent);
    }

    public void viewHistory() {
        Intent intent = new Intent(this, ReadHistory.class);
        startActivity(intent);
    }
    public void goToViewBook() {
        String isbn = DataUtility.getCurrentBook().getIsbn();
        Intent intent = new Intent(this, ViewBook.class);
        intent.putExtra("isbn", isbn);
        startActivity(intent);
    }

    public static void createUserLibrary(){
        BookBuzzDataModel hitchhikersGuideToTheGalaxy = new BookBuzzDataModel("Hitchhikers guide to the Galaxy", "ISBN-54321");
        hitchhikersGuideToTheGalaxy.setAuthor("Douglas Adams");
        hitchhikersGuideToTheGalaxy.setImage(R.drawable.hitchhikers_guide);
        hitchhikersGuideToTheGalaxy.setPages("123");
        hitchhikersGuideToTheGalaxy.setYear("1970");
        hitchhikersGuideToTheGalaxy.setStatus("Read");
        hitchhikersGuideToTheGalaxy.setGenre("SciFi");
        hitchhikersGuideToTheGalaxy.setBookmark("50");


        BookBuzzDataModel braveNewWorld = new BookBuzzDataModel("Brave New World", "ISBN-74321");
        braveNewWorld.setImage(R.drawable.brave_new_world);
        braveNewWorld.setAuthor("Aldous Huxley");
        braveNewWorld.setPages("300");
        braveNewWorld.setYear("1950");
        braveNewWorld.setGenre("SciFi");
        braveNewWorld.setStatus("Want to read");
        braveNewWorld.setBookmark("0");

        DataUtility.addABook(hitchhikersGuideToTheGalaxy);
        DataUtility.addABook(braveNewWorld);


    }

    public static void createDatabase(){


        BookBuzzDataModel grapesOfWrath = new BookBuzzDataModel("The Grapes of Wrath", "ISBN-5678");
        grapesOfWrath.setAuthor("John Steinbeck");
        grapesOfWrath.setImage(R.drawable.grapes_of_wrath);
        grapesOfWrath.setPages("300");
        grapesOfWrath.setGenre("Classic");
        grapesOfWrath.setYear("1923");


        BookBuzzDataModel LOTR = new BookBuzzDataModel("The Fellowship of the Ring", "ISBN-3657");
        LOTR.setAuthor("J. R. R. Tolkein");
        LOTR.setImage(R.drawable.lotr_book);
        LOTR.setPages("600");
        LOTR.setGenre("Fantasy");
        LOTR.setYear("1950");

        BookBuzzDataModel catch22 = new BookBuzzDataModel("Catch 22", "ISBN-23657");
        catch22.setImage(R.drawable.catch_22);
        catch22.setAuthor("Joseph Heller");
        catch22.setPages("300");
        catch22.setGenre("Satire");
        catch22.setYear("1950");

        BookBuzzDataModel lordOfLight = new BookBuzzDataModel("Lord of Light", "ISBN-24356");
        lordOfLight.setAuthor("Roger Zelazny");
        lordOfLight.setImage(R.drawable.lord_of_light);
        lordOfLight.setPages("400");
        lordOfLight.setGenre("SciFi");
        lordOfLight.setYear("1923");

        BookBuzzDataModel restaurantAtEOU = new BookBuzzDataModel("Restaurant at the End of the Universe", "ISBN-14356");
        restaurantAtEOU.setImage(R.drawable.restaurant_at_the_end_of_the_universe);
        restaurantAtEOU.setAuthor("Douglas Adams");
        restaurantAtEOU.setPages("200");
        restaurantAtEOU.setGenre("SciFi");
        restaurantAtEOU.setYear("1970");

        BookBuzzDataModel lifeUniverseEverything = new BookBuzzDataModel("Life the UNiverse + Everything", "ISBN-54586");
        lifeUniverseEverything.setImage(R.drawable.life_the_universe_and_everything);
        lifeUniverseEverything.setAuthor("Douglas Adams");
        lifeUniverseEverything.setPages("200");
        lifeUniverseEverything.setGenre("SciFi");
        lifeUniverseEverything.setYear("1972");


        DataUtility.addBookDB(grapesOfWrath);
        DataUtility.addBookDB(LOTR);
        DataUtility.addBookDB(catch22);
        DataUtility.addBookDB(lordOfLight);
        DataUtility.addBookDB(restaurantAtEOU);
        DataUtility.addBookDB(lifeUniverseEverything);

    }


}