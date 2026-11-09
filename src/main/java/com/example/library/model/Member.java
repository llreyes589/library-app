package main.java.com.example.library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    private final String memberId;
    private final String name;
    private final List<String> borrowedIsbn = new ArrayList<>();

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<String> getBorrowedIsbn() {
        return Collections.unmodifiableList(borrowedIsbn);
    }

    public void addBorrowedBook(String isbn) {
        borrowedIsbn.add(isbn);
    }

    public void removeBorrowedBook(String isbn) {
        borrowedIsbn.remove(isbn);
    }
}
