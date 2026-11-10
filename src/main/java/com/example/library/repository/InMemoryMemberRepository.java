package main.java.com.example.library.repository;

import main.java.com.example.library.model.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
}
