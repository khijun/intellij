package edu.du.sb1030.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.du.sb1030.dto.AuthInfo;
import edu.du.sb1030.dto.LoginCommand;
import edu.du.sb1030.exception.WrongIdPasswordException;
import edu.du.sb1030.service.AuthService;
import edu.du.sb1030.validator.LoginCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand, @CookieValue(value="REMEMBER", required = false) Cookie rCookie) {
		if(rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse	response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors())
			return "login/loginForm";
		try {
			AuthInfo authInfo = authService.authenicate(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo);

			Cookie rCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rCookie.setPath("/");
			if(loginCommand.isRememberEmail()) {
				rCookie.setMaxAge(60*60*24*30);
			}else{
				rCookie.setMaxAge(0);
			}
			response.addCookie(rCookie);

			return "login/loginSuccess";
		}catch(WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
	
}
