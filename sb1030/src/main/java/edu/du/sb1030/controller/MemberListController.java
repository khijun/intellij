package edu.du.sb1030.controller;

import java.util.List;

import edu.du.sb1030.dao.MemberDao;
import edu.du.sb1030.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class MemberListController {
    @Autowired
    private MemberDao memberDao;

    @RequestMapping("/members")
    public String list(@ModelAttribute("cmd") @Valid ListCommand listCommand, Model model, Errors errors) {
        if(errors.hasErrors()) {
            return "member/memberList";
        }
        if(listCommand.getFrom() != null && listCommand.getTo() != null) {
            List<Member> members = memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
            model.addAttribute("members", members);
        }
        return "member/memberList";
    }

}
