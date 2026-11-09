package main.java.com.example.library.repository;

import main.java.com.example.library.model.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(String memberId);
}
