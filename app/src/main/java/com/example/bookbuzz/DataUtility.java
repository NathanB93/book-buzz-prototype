package com.example.bookbuzz;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtility {
    private static Map<String, BookBuzzDataModel> books = new HashMap<>();

    private static Map<String, BookBuzzDataModel> database = new HashMap<>();

    private static BookBuzzDataModel currentBook;

    public static BookBuzzDataModel getCurrentBook() { return currentBook;
    }

    public static void setCurrentBook(final BookBuzzDataModel currentBookToset) {
        currentBook = currentBookToset;
    }



    public static void addABook(final BookBuzzDataModel bookBuzzDataModel) {
        books.put(bookBuzzDataModel.getIsbn(), bookBuzzDataModel);
    }

    public static void addBookDB(final BookBuzzDataModel book) {
        database.put(book.getIsbn(), book);
    }

    public static BookBuzzDataModel getABookByIsbn(final String isbn) {
        return books.get(isbn);
    }

    public static BookBuzzDataModel getBookByIsbnDB(final String isbn) {
        return database.get(isbn);
    }
    public static ArrayList<BookBuzzDataModel> getBooks() {

        return new ArrayList<>(books.values());
    }

    public static ArrayList<BookBuzzDataModel> getBooksDB() {
        return new ArrayList<>(database.values());
    }


    public static ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();

        for (BookBuzzDataModel book : getBooks()) {
            names.add(book.getCurrentBookName());

        }
        return names;
    }
    public static ArrayList<String> getNamesDB() {
        ArrayList<String> names = new ArrayList<>();

        for (BookBuzzDataModel book : getBooksDB()) {
            names.add(book.getCurrentBookName());

        }
        return names;
    }
    public static ArrayList<String> getAuthors() {
        ArrayList<String> authors = new ArrayList<>();

        for (BookBuzzDataModel book : getBooks()) {
            authors.add(book.getAuthor());

        }
        return authors;
    }

    public static ArrayList<String> getAuthorsDB() {
        ArrayList<String> authors = new ArrayList<>();

        for (BookBuzzDataModel book : getBooksDB()) {
            authors.add(book.getAuthor());

        }
        return authors;
    }
    public static ArrayList<String> getAllStatus() {
        ArrayList<String> allStatus = new ArrayList<>();

        for (BookBuzzDataModel book : getBooks()) {
            allStatus.add(book.getStatus());

        }
        return allStatus;
    }

    public static ArrayList<String> getBookmarks() {
        ArrayList<String> bookmarks = new ArrayList<>();

        for (BookBuzzDataModel book : getBooks()) {
            bookmarks.add(book.getBookmark());

        }
        return bookmarks;
    }

    public static ArrayList<String> getISBNs() {
        ArrayList<String> isbn = new ArrayList<>();

        for (BookBuzzDataModel book : getBooks()) {
            isbn.add(book.getIsbn());

        }
        return isbn;

    }

    public static ArrayList<String> getISBNsDB() {
        ArrayList<String> isbn = new ArrayList<>();

        for (BookBuzzDataModel book : getBooksDB()) {
            isbn.add(book.getIsbn());

        }
        return isbn;

    }
    public static int[] getImages() {
        int count = 0;
        int[] images = new int[getBooks().size()];

        for (BookBuzzDataModel book : getBooks()) {
            images[count] = book.getImage();
            count++;

        }
        return images;
    }

    public static int[] getImagesDB() {
        int count = 0;
        int[] images = new int[getBooksDB().size()];

        for (BookBuzzDataModel book : getBooksDB()) {
            images[count] = book.getImage();
            count++;

        }
        return images;
    }
    public static void removeBook(String isbn) {
        books.remove(isbn);
    }


    public static void setStatus(String isbn, String status) {

        books.get(isbn).setStatus(status);
    }


}

