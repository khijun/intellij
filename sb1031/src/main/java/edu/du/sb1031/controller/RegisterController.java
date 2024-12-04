package edu.du.sb1031.controller;

import edu.du.sb1031.dto.Define;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

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
    public String register(@ModelAttribute Member member){
        member.setRole(Define.USER);
        memberService.save(member);
        return "redirect:/";
    }

    @PostMapping("/checkUsername")
    @ResponseBody
    public ResponseEntity<Map<String, String>> checkUsername(@RequestBody Map<String, String> requestData) {
        // 클라이언트에서 전달된 'username' 데이터 가져오기
        String username = requestData.get("username");

        // 사용자 아이디 존재 여부 체크
        String message = memberService.existsByUsername(username)
                ? "이미 존재하는 아이디입니다."
                : "사용가능한 아이디입니다.";

        // 응답으로 보낼 객체 생성
        Map<String, String> response = new HashMap<>();
        response.put("message", message);

        // 사용가능한 아이디라면 200 OK 반환, 아니면 409 Conflict 반환
        if (memberService.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
