package edu.du.sb1031.controller;

import edu.du.sb1031.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String form() {
    	return "/login/login";
    }

//    @PostMapping
//    public String submit(@Valid LoginCommand loginCommand, Errors errors, HttpSession session) {
//
//        if (errors.hasErrors()) {
//            return "/login/loginForm";
//        }
//        try {
//            AuthInfo authInfo = authService.authenticate(
//                    loginCommand.getUsername(),
//                    loginCommand.getPassword());
//            session.setAttribute("authInfo", authInfo);
//            System.out.println(authInfo.getId() + " 세션 저장!");
//            return "redirect:/";
//        } catch (WrongUsernamePasswordException e) {
//            errors.reject("idPasswordNotMatching");
//            return "/login/loginForm";
//        }
//    }
}
