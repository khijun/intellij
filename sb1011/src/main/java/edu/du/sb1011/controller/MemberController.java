package edu.du.sb1011.controller;

import edu.du.sb1011.dto.MemberDto;
import edu.du.sb1011.exception.LoginFailedException;
import edu.du.sb1011.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberService ms;

    @GetMapping("/member/loginForm.do")
    public String loginForm() {
        return "/member/loginForm";
    }

    @PostMapping("/member/login.do")
    public String login(HttpServletRequest request) {
        MemberDto dto = new MemberDto(-1, request.getParameter("name"), "", request.getParameter("password"), "");
        try {
            ms.login(request.getSession(),dto);
            return "/member/loginForm";
        }catch(LoginFailedException e) {
            e.printStackTrace();
            return "redirect:/board/openBoardList.do";
        }
    }
}
