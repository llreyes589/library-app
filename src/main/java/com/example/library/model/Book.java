package main.java.com.example.library.model;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private int availableCopies;

    public Book(String isbn, String title, String author, int totalCopies) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN is required");
        }
        if (totalCopies < 0) {
            throw new IllegalArgumentException("Copies cannot be negative");
        }

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.availableCopies = totalCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void borrowCopy() {
        if (!isAvailable()) {
            throw new IllegalStateException("No copies available");
        }
        availableCopies--;
    }

    public void returnCopy() {
        availableCopies++;
    }

}
