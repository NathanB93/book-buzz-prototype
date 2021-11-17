package com.example.bookbuzz;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteViewAdaptor extends RecyclerView.Adapter<NoteViewAdaptor.NoteViewHolder> {

    private Context context; // initialize variable of type Context
    private List<NoteModel> currentBookNotes; //initialize the list of current booka notes.

    public NoteViewAdaptor(Context context, List<NoteModel> currentBookNotes) {
        this.context =context;
        this.currentBookNotes = currentBookNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_row, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        String date = currentBookNotes.get(position).getNoteDate();
        String text = currentBookNotes.get(position).getNoteText();
        String toInsertText = text + "\n";
        holder.dateTextView.setText(date);
        holder.noteTextView.setText(toInsertText);
    }

    /***
     * method will count of books notes.
     * @return number of book notes.
     */
    @Override
    public int getItemCount() {
        return currentBookNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView noteTextView;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.textViewNoteDate);
            noteTextView = itemView.findViewById(R.id.textViewNoteText);

//            noteTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, "Create Edit dialogs", Toast.LENGTH_SHORT).show();
//                }
//            });
        }


    }
}
