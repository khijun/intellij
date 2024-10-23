package edu.du.sb1021.service;

import edu.du.sb1021.entity.Member;
import edu.du.sb1021.entity.Role;
import edu.du.sb1021.repository.MemberRepository;
import edu.du.sb1021.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    final MemberRepository mr;
    final RoleRepository rr;

    public void save(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        mr.save(member);
    }
    public List<Member> findAll(){
        return mr.findAll();
    }
}
