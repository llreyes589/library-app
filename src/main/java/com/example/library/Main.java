package main.java.com.example.library;

import main.java.com.example.library.fine.StandardFinePolicy;
import main.java.com.example.library.model.Book;
import main.java.com.example.library.model.Member;
import main.java.com.example.library.repository.InMemoryBookRepository;
import main.java.com.example.library.repository.InMemoryMemberRepository;
import main.java.com.example.library.service.LibraryService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var bookRepository = new InMemoryBookRepository();
        var memberRepository = new InMemoryMemberRepository();

        var library = new LibraryService(
                bookRepository,
                memberRepository,
                new StandardFinePolicy(5.0)
        );

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addBook(library);
                case "2" -> listBooks(library);
                case "3" -> registerMember(library);
                case "4" -> listMembers(library);
                case "5" -> borrowBook(library);
                case "6" -> returnBook(library);
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Library App =====");
        System.out.println("1. Add book");
        System.out.println("2. List books");
        System.out.println("3. Register member");
        System.out.println("4. List members");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addBook(LibraryService library) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Author: ");
        String author = scanner.nextLine().trim();

        System.out.print("Available copies: ");
        int copies = Integer.parseInt(scanner.nextLine().trim());

        try {
            library.addBook(new Book(isbn, title, author, copies));
            System.out.println("Book added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listBooks(LibraryService library) {
        var books = library.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- Books ---");
        for (Book book : books) {
            System.out.printf(
                    "ISBN: %s | %s by %s | Available: %d%n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getAvailableCopies()
            );
        }
    }

    private static void registerMember(
            LibraryService library
    ) {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        try {
            library.registerMember(new Member(memberId, name));
            System.out.println("Member registered successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void listMembers(
            LibraryService library
    ) {
        var members = library.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.println("\n-- Members ---");
        for (Member member : members) {
            System.out.printf(
                    "ID: %s | Name: %s | Borrowed: %s%n",
                    member.getMemberId(),
                    member.getName(),
                    member.getBorrowedIsbn()
            );
        }
    }

    private static void borrowBook(LibraryService library) {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine().trim();

        try {
            library.borrowBook(memberId, isbn);
            System.out.println("Book borrowed successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook(LibraryService library) {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Days late:");
        int daysLate = Integer.parseInt(scanner.nextLine().trim());

        try {
            double fine = library.returnBook(memberId, isbn, daysLate);
            System.out.printf("Book returned. Fine: PHP %.2f%n", fine);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
