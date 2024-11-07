package edu.du.sb1031.service;

import edu.du.sb1031.entity.Member;
import edu.du.sb1031.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository mr;

    public Member findById(Long id) {
        return mr.findById(id).get();
    }

    public void save(Member member){
        mr.save(member);
    }

    public List<Member> findAll(){return mr.findAll();}

    public Member findByUsername(String username) {
        return mr.findByUsername(username);
    }
}
