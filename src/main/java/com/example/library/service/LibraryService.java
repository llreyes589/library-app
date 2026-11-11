package main.java.com.example.library.service;

import main.java.com.example.library.fine.FinePolicy;
import main.java.com.example.library.model.Book;
import main.java.com.example.library.model.Member;
import main.java.com.example.library.repository.BookRepository;
import main.java.com.example.library.repository.MemberRepository;

import java.util.List;

public class LibraryService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final FinePolicy finePolicy;

    public LibraryService(
            BookRepository bookRepository,
            MemberRepository memberRepository,
            FinePolicy finePolicy
    ) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.finePolicy = finePolicy;
    }

    public void addBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException(
                    "A book with this ISBN already exists."
            );
        }

        bookRepository.save(book);
    }

    public void registerMember(Member member) {
        if (memberRepository.existsById(member.getMemberId())) {
            throw new IllegalArgumentException(
                    "A member with this ID already exists."
            );
        }
        memberRepository.save(member);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void borrowBook(String memberId, String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        member.addBorrowedBook(isbn);
        book.borrowCopy();
    }

    public double returnBook(String memberId, String isbn, int daysLate) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        member.removeBorrowedBook(isbn);
        book.returnCopy();

        return finePolicy.calculate(daysLate);

    }
}
