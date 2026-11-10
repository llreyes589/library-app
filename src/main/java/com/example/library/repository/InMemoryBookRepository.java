package main.java.com.example.library.repository;

import main.java.com.example.library.model.Book;

import java.util.*;

public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> booksByIsbn = new HashMap<>();

    @Override
    public void save(Book book) {
        booksByIsbn.put(book.getIsbn(), book);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(booksByIsbn.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(booksByIsbn.values());
    }
}
