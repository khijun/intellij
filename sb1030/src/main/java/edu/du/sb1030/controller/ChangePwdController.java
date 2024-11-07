package edu.du.sb1030.controller;

import javax.servlet.http.HttpSession;

import edu.du.sb1030.dto.AuthInfo;
import edu.du.sb1030.exception.WrongIdPasswordException;
import edu.du.sb1030.service.ChangePasswordService;
import edu.du.sb1030.dto.ChangePwdCommand;
import edu.du.sb1030.validator.ChangePwdCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

	@Autowired
	private ChangePasswordService changePasswordService;

	@GetMapping
	public String form(
			@ModelAttribute("command") ChangePwdCommand pwdCmd, Model model) {
//		if(pwdCmd==null)
			model.addAttribute("passwordForm", new ChangePwdCommand());
		return "edit/changePwdForm";
	}

	@PostMapping
	public String submit(
			@ModelAttribute("command") ChangePwdCommand pwdCmd,
			Errors errors,
			HttpSession session) {
		new ChangePwdCommandValidator().validate(pwdCmd, errors);
		if (errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		try {
			changePasswordService.changePassword(
					authInfo.getEmail(),
					pwdCmd.getCurrentPassword(),
					pwdCmd.getNewPassword());
			return "edit/changedPwd";
		} catch (WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
