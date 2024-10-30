package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    final MemberRepository mr;
    final EntityManagerFactory emf;

    public void save(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        mr.save(member);
    }
    public List<Member> findAll(){
        return mr.findAll();
    }

//    public Member getMember(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//
//            if (principal instanceof UserDetails) {
//                UserDetails userDetails = (UserDetails) principal;
//
//                EntityManager em = emf.createEntityManager();
//
//                Member member = em.createQuery("select m from Member m where username = :username",Member.class)
//                        .setParameter("username", ((UserDetails) principal).getUsername()).getSingleResult();
//                if(member == null||member.getPassword().equals(((UserDetails) principal).getPassword()))
//                    return null;
//                return member;
//            }
//        }
//        return null;
//    }
}
