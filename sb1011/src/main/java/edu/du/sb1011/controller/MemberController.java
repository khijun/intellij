package edu.du.sb1011.controller;

import edu.du.sb1011.dto.MemberDto;
import edu.du.sb1011.exception.LoginFailedException;
import edu.du.sb1011.service.MemberService;
import edu.du.sb1011.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberService ms;
    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @GetMapping("/member/loginForm.do")
    public String loginForm() {
        return "/member/loginForm";
    }

    @PostMapping("/member/login.do")
    public String login(Model model, HttpServletRequest request) {
        MemberDto dto = new MemberDto(-1, "", request.getParameter("id"), request.getParameter("password"), "");
        try {
            request.getSession().setAttribute("member", ms.login(dto));
            return "redirect:/board/openBoardList.do";
        }catch(LoginFailedException e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getClass().getName());// 임시 예외처리
            return "/member/memberError.html";// 오류페이지
        }
    }

    @GetMapping("/member/joinForm.do")
    public String joinForm() {
        return "/member/joinForm";
    }

    @PostMapping("/member/join.do")
    public String join(Model model, HttpServletRequest request) {
        try {
            MemberDto dto = new MemberDto(-1, request.getParameter("name"), request.getParameter("id"), request.getParameter("password"), "");
            memberServiceImpl.addOne(dto);
        } catch (Exception e) {
            return "/member/memberError.html";
        }
        return "redirect:/board/boardList.do";// 로그인 성공 페이지로 수정 필요
    }
}
