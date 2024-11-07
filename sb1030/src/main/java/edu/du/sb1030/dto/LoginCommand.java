package edu.du.sb1030.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginCommand {
	private String email;
	private String password;
	private boolean rememberEmail;
}
