package main.java.com.example.library;

import main.java.com.example.library.fine.StandardFinePolicy;
import main.java.com.example.library.model.Book;
import main.java.com.example.library.model.Member;
import main.java.com.example.library.repository.InMemoryBookRepository;
import main.java.com.example.library.repository.InMemoryMemberRepository;
import main.java.com.example.library.service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var bookRepository = new InMemoryBookRepository();
        var memberRepository = new InMemoryMemberRepository();
        var library = new LibraryService(
                bookRepository,
                memberRepository,
                new StandardFinePolicy(5.0)
        );

        library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 3));
        memberRepository.save(new Member("M001", "Ana"));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine();

        try {
            library.borrowBook(memberId, isbn);
            System.out.println("Book borrowed successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
