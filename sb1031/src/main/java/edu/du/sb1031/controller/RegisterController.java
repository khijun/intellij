package edu.du.sb1031.controller;

import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {
    private final MemberService memberService;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("member", new Member());
        return "/register/register";
    }

    @PostMapping
    public String register(@ModelAttribute Member member, RedirectAttributes redirectAttributes){
        memberService.save(member);
        redirectAttributes.addFlashAttribute("message", "회원가입 성공!");
        return "redirect:/";
    }

    @GetMapping("/checkUsername")
    @ResponseBody
    public ResponseEntity<?> checkUsername(@RequestParam String username){
        return memberService.existsByUsername(username)?ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.")
                :ResponseEntity.ok("사용가능한 아이디입니다.");
    }
}
