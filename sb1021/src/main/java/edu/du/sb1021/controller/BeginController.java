package edu.du.sb1021.controller;

import edu.du.sb1021.entity.Member;
import edu.du.sb1021.entity.Role;
import edu.du.sb1021.repository.MemberRepository;
import edu.du.sb1021.service.MemberService;
import edu.du.sb1021.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Controller
public class BeginController {
    @Autowired
    MemberRepository mr;
    @Autowired
    MemberService ms;
    @Autowired
    RoleService rs;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String root() {
        return "/sample/all";
    }
    @PostConstruct
    public void init(){
        Role admin = Role.builder()
                .name("ADMIN")
                .build();
        Role user = Role.builder()
                .name("USER")
                .build();
        rs.save(admin);
        rs.save(user);
        Optional<Role> curr = rs.findById(2L);
        Member member = Member.builder()
                .username("hong")
                .password("1234")
                .email("hong@co.kr")
                .role(curr.get())
                .build();
        ms.save(member);
    }
}
