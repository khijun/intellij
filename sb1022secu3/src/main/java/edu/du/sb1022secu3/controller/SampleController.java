package edu.du.sb1022secu3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/")
public class SampleController {
    @GetMapping("accessDenied")
    public void accessDenied(){}
    @GetMapping("member")
    public void member(){}
    @GetMapping("admin")
    public void admin(){}
    @GetMapping("all")
    public void all(){}
}
