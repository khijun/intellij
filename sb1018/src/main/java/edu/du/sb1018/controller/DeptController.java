package edu.du.sb1018.controller;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {

    final DeptService ds;

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        model.addAttribute("depts", ds.getAll());
        return "dept/list";
    }

//    public String info(){
//        return "info";
//    }

    @GetMapping("/writeForm")
    public String write(){
        return "dept/writeForm";
    }

    @PostMapping("/write")
    public String writeForm(@ModelAttribute Dept dept) throws Exception {
        ds.add(dept);
        return "redirect:/dept/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws Exception {
        ds.del(id);
        return "redirect:/dept/list";
    }
}
