package edu.du.sb1021.controller;

import edu.du.sb1021.entity.Member;
import edu.du.sb1021.service.MemberService;
import edu.du.sb1021.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sample/")
@RequiredArgsConstructor
public class SampleController {

    final MemberService ms;
    final RoleService rs;

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
