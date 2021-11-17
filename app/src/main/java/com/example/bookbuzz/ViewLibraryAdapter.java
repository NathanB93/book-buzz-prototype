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

public class ViewLibraryAdapter extends RecyclerView.Adapter<ViewLibraryAdapter.LibraryViewHolder> implements Filterable {

    ArrayList<BookBuzzDataModel> library;
    ArrayList<BookBuzzDataModel> fullLibrary;
    Context context;
    String criteria;
    Boolean cb;



    public ViewLibraryAdapter(Context ct, ArrayList<BookBuzzDataModel> library){
        context = ct;
        this.library = library;
        this.criteria = "title";
        fullLibrary = new ArrayList<>(library);
        this.cb = false;
    }


    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public void setCB(Boolean cb){
        this.cb = cb;
    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_library_row, parent, false);
        return new LibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, final int position) {

        BookBuzzDataModel positionBook = library.get(position);
        holder.book_title_txt.setText(positionBook.getCurrentBookName());
        holder.book_author_txt.setText(positionBook.getAuthor());
        holder.book_status_txt.setText(positionBook.getStatus());
        holder.bookmark_txt.setText(positionBook.getBookmark());
        holder.isbn_txt.setText(positionBook.getIsbn());
        holder.viewLibImageView.setImageResource(positionBook.getImage());
        holder.viewLibraryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb) {

                    DataUtility.setCurrentBook(positionBook);
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ViewBook.class);
                    intent.putExtra("isbn", positionBook.getIsbn());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return library.size();
    }

    public class LibraryViewHolder extends RecyclerView.ViewHolder {

        TextView book_title_txt, book_author_txt, book_status_txt, bookmark_txt, isbn_txt;
        ImageView viewLibImageView;
        ConstraintLayout viewLibraryLayout;


        public LibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            book_title_txt = itemView.findViewById(R.id.book_title_txt_vl_row);
            book_author_txt = itemView.findViewById(R.id.book_author_txt_vl_row);
            book_status_txt = itemView.findViewById(R.id.book_status_txt_vl_row);
            bookmark_txt = itemView.findViewById(R.id.book_bookmark_txt_vl_row);
            isbn_txt = itemView.findViewById(R.id.book_isbn_txt_vl_row);
            viewLibImageView = itemView.findViewById(R.id.viewLibraryImageView);
            viewLibraryLayout = itemView.findViewById(R.id.viewLibraryLayout);
            isbn_txt.setVisibility(View.GONE);

        }
    }

    @Override
    public Filter getFilter() {
        return libraryFilter;
    }

    private Filter libraryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<BookBuzzDataModel> filteredLibrary = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredLibrary.addAll(fullLibrary);

            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();

                if (criteria.equals("title")) {
                    for (BookBuzzDataModel book : fullLibrary) {
                        if (book.getCurrentBookName().toLowerCase().contains(filterPattern)) {
                            filteredLibrary.add(book);
                        }
                    }
                } else if (criteria.equals("author")) {
                    for (BookBuzzDataModel book : fullLibrary) {
                        if (book.getAuthor().toLowerCase().contains(filterPattern)) {
                            filteredLibrary.add(book);
                        }
                    }
                } else if (criteria.equals("status")) {
                    for (BookBuzzDataModel book : fullLibrary) {
                        if (book.getStatus().toLowerCase().contains(filterPattern)) {
                            filteredLibrary.add(book);
                        }
                    }
                } else if (criteria.equals("genre")) {
                    for (BookBuzzDataModel book : fullLibrary) {
                        if (book.getGenre().toLowerCase().contains(filterPattern)) {
                            filteredLibrary.add(book);
                        }
                    }
                } else if (criteria.equals("year")) {
                    for (BookBuzzDataModel book : fullLibrary) {
                        if (book.getYear().toLowerCase().contains(filterPattern)) {
                            filteredLibrary.add(book);
                        }
                    }
                } else if (criteria.equals("isbn")) {
                    for (BookBuzzDataModel book : fullLibrary) {
                        if (book.getIsbn().toLowerCase().contains(filterPattern)) {
                            filteredLibrary.add(book);
                        }
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredLibrary;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            library.clear();

            library.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}