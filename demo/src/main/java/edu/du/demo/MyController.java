package edu.du.demo;

import edu.du.demo.dao.ISimpleBbsDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {
    @Autowired
    ISimpleBbsDao dao;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("list", dao.listDao());
        return "list";
    }

    @GetMapping("/view")
    public String view(HttpServletRequest request,Model model){
        String sId = request.getParameter("id");
        System.out.println(sId);
        model.addAttribute("dto", dao.viewDao(sId));
        return "view";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request){
        dao.deleteDao(request.getParameter("id"));
        return "redirect:/list";
    }

    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(HttpServletRequest request){
        dao.writeDao(request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("content"));
        return "redirect:/list";
    }

    @GetMapping("updateForm")
    public String updateForm(HttpServletRequest request,Model model){
        model.addAttribute("dto",dao.viewDao(request.getParameter("id")));
        return "writeForm";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request){
        dao.updateDao(request.getParameter("id"),
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("content"));

        return "redirect:/list";
    }
}
