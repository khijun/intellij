package com.example.demo.member.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.Role;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class BeginController {
    @Autowired
    MemberRepository mr;
    @Autowired
    MemberService ms;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String root() {
        return "/sample/all";
    }
    @PostConstruct
    public void init(){
        Member member = Member.builder()
                .username("hong")
                .password("1234")
                .email("hong@co.kr")
                .role(Role.user)
                .build();
        ms.save(member);
    }
}
