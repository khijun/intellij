package com.example.demo.member.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/")
@RequiredArgsConstructor
public class SampleController {

    final MemberService ms;

    @GetMapping("accessDenied")
    public void accessDenied(){}
    @GetMapping("member")
    public void member(){}
    @GetMapping("admin")
    public void admin(Model model){
        model.addAttribute("members", ms.findAll());
    }
    @GetMapping("all")
    public void all(){}
    @GetMapping("joinForm")
    public void joinForm(){}

    @PostMapping("join")
    public String join(@ModelAttribute Member member){
        ms.save(member);
        return "redirect:/";
    }
}
