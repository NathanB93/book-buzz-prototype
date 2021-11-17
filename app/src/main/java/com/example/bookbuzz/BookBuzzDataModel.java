package com.example.bookbuzz;

import java.util.ArrayList;
import java.util.List;

/***
 * This class holds the book and list of notes.
 */
public class BookBuzzDataModel {
    private String isbn;
    private String currentBookName;
    private String author;
    private String status;
    private String bookmark;
    private String year;
    private String genre;
    private String pages;
    private List<NoteModel> notes;
    private int image;

//    private List<String> notes;

    public BookBuzzDataModel(final String bookName, final String bookIsbn) {
        this.currentBookName = bookName;
        this.isbn = bookIsbn;
        notes = new ArrayList<>();
        this.bookmark = "0";
    }

    public String getPages(){
        return pages;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getAuthor(){

        return author;
    }
    public void setAuthor(String author) {

        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus(){
        return this.status;
    }
    public String getBookmark(){
        return this.bookmark;
    }

    public void setBookmark(String page) {
        this.bookmark = page;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /***
     * Funtion used to get ISBN of book
     * @return ISBN of type string.
     */
    public int getImage() {
        return image;
    }
    public void setImage(int drawable) {
        this.image = drawable;
    }
    public String getIsbn() {
        return isbn;
    }

    /***
     * function to use set the value of ISBN
     * @param isbn of string type
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /***
     * function to get value of current book name.
     * @return string value of current book name.
     */
    public String getCurrentBookName() {
        return currentBookName;
    }

    /***
     * Function to set current book name
     * @param currentBookName of type string
     */
    public void setCurrentBookName(String currentBookName) {
        this.currentBookName = currentBookName;
    }

    /***
     * Function to get list of notes
     * @return value of notes.
     */
    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }
}