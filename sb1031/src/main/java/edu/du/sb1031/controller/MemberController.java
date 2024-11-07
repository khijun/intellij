package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.entity.Order;
import edu.du.sb1031.service.MemberService;
import edu.du.sb1031.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {
    private final MemberService ms;
    private final OrderService os;

    @GetMapping("registerForm")
    public void registerForm(){}

    @GetMapping("myItem")
    public String myItem(Model model, HttpServletRequest request){
        try{
            Long memberId = ((AuthInfo)request.getSession(true).getAttribute("authInfo")).getId();
            Member member = ms.findById(memberId);
            List<Order> orders = os.findByMember(member);
            model.addAttribute("orders", orders);
        }catch (Exception e){
            System.out.println("MemberController.myItem: 인증오류");
            return "redirect:/";
        }
        return "/member/myItem";
    }

    @PostMapping("save")
    public String save(Member member){
        try {
            ms.save(member);
            return "redirect:/myItem";
        }catch (Exception e){
            System.out.println("실패");
            return "redirect:/myItem";
        }
    }
}
