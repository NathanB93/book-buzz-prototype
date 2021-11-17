package com.example.bookbuzz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchDatabaseAdapter extends RecyclerView.Adapter<SearchDatabaseAdapter.DatabaseViewHolder> implements Filterable {
    String criteria;
    ArrayList<BookBuzzDataModel> database;
    ArrayList<BookBuzzDataModel> fullDatabase;
    Context context;


    public SearchDatabaseAdapter(Context ct, ArrayList<BookBuzzDataModel> database) {
        this.criteria = "title";
        context = ct;
        this.database = database;
        fullDatabase = new ArrayList<>(database);
    }
    public void setCriteria(String criteria){
        this.criteria = criteria;
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_database_row, parent, false);
        return new DatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, final int position) {

        BookBuzzDataModel positionBook = database.get(position);

        holder.searchDBImageView.setImageResource(positionBook.getImage());
        holder.book_title_txt.setText(positionBook.getCurrentBookName());
        holder.book_author_txt.setText(positionBook.getAuthor());
        holder.genre_txt.setText(positionBook.getGenre());
        holder.isbn_txt.setText(positionBook.getIsbn());


        holder.searchDBLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewBookDatabase.class);
                intent.putExtra("isbn", positionBook.getIsbn());
                context.startActivity(intent);

            }
        });
        holder.addToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String db = "db";
                Intent intent = new Intent(context, EnterBookDetails.class);
                intent.putExtra("isbn",positionBook.getIsbn());
                intent.putExtra("db", db);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return database.size();
    }

    public class DatabaseViewHolder extends RecyclerView.ViewHolder {

        TextView book_title_txt, book_author_txt, genre_txt, isbn_txt;
        ImageView searchDBImageView;
        ConstraintLayout searchDBLayout;
        ImageView addToLibrary;


        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            addToLibrary = itemView.findViewById(R.id.addToLibrary);
            book_title_txt = itemView.findViewById(R.id.book_title_txt_db_row);
            book_author_txt = itemView.findViewById(R.id.book_author_txt_db_row);
            isbn_txt = itemView.findViewById(R.id.book_isbn_txt);
            genre_txt = itemView.findViewById(R.id.book_genre_txt_db_row);
            searchDBImageView = itemView.findViewById(R.id.searchDBImageView);
            searchDBLayout = itemView.findViewById(R.id.searchDBLayout);
            isbn_txt.setVisibility(View.GONE);



        }
    }
    @Override
    public Filter getFilter() {
        return databaseFilter;
    }

    private Filter databaseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<BookBuzzDataModel> filteredDatabase = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredDatabase.addAll(fullDatabase);

            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();

                if (criteria.equals("title")) {
                    for (BookBuzzDataModel book : fullDatabase) {
                        if (book.getCurrentBookName().toLowerCase().contains(filterPattern)) {
                            filteredDatabase.add(book);


                        }
                    }
                } else if (criteria.equals("author")) {
                    for (BookBuzzDataModel book : fullDatabase) {
                        if (book.getAuthor().toLowerCase().contains(filterPattern)) {
                            filteredDatabase.add(book);
                        }
                    }
                } else if (criteria.equals("genre")) {
                    for (BookBuzzDataModel book : fullDatabase) {
                        if (book.getGenre().toLowerCase().contains(filterPattern)) {
                            filteredDatabase.add(book);
                        }
                    }
                } else if (criteria.equals("year")) {
                    for (BookBuzzDataModel book : fullDatabase) {
                        if (book.getYear().toLowerCase().contains(filterPattern)) {
                            filteredDatabase.add(book);
                        }
                    }
                } else if (criteria.equals("isbn")) {
                    for (BookBuzzDataModel book : fullDatabase) {
                        if (book.getIsbn().toLowerCase().contains(filterPattern)) {
                            filteredDatabase.add(book);
                        }
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredDatabase;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            database.clear();

            database.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

}

