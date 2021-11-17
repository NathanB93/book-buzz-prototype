package com.example.bookbuzz;

public class NoteModel {

    private String noteDate;
    private String noteText;

    public NoteModel(final String noteDate, final String noteText) {
        this.noteDate = noteDate;
        this.noteText = noteText;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
