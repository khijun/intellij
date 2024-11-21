package edu.du.sb1031.service;

import edu.du.sb1031.entity.Member;
import edu.du.sb1031.exception.MemberNotFoundException;
import edu.du.sb1031.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository mr;
    @Autowired
    private PasswordEncoder passwordEncoder;// 순환참조

    public Member findById(Long id) {
        return mr.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public void save(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        mr.save(member);
    }

    public List<Member> findAll(){return mr.findAll();}

    public Member findByUsername(String username) {
        return mr.findByUsername(username).orElseThrow(MemberNotFoundException::new);
    }

    public void existsById(Long id) {
        if(!mr.existsById(id)) throw new MemberNotFoundException();
    }

    public boolean existsByUsername(String username) {
        return mr.existsByUsername(username);
    }
}
