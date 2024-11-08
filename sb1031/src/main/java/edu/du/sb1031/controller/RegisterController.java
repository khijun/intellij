package edu.du.sb1031.controller;

import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {
    private final MemberService memberService;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("member", new Member());
        return "/register/registerForm";
    }

    @PostMapping
    public String register(Member member){
        memberService.save(member);
        return "redirect:/";
    }
}
