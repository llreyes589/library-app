package main.java.com.example.library.repository;

import main.java.com.example.library.model.Member;

import java.util.*;

public class InMemoryMemberRepository implements MemberRepository {
    private final Map<String, Member> membersById = new HashMap<>();

    @Override
    public Optional<Member> findById(String memberId) {
        return Optional.ofNullable(membersById.get(memberId));
    }

    @Override
    public void save(Member member) {
        membersById.put(member.getMemberId(), member);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(membersById.values());
    }

    @Override
    public boolean existsById(String memberId) {
        return membersById.containsKey(memberId);
    }
}
