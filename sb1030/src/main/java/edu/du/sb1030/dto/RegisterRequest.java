package edu.du.sb1030.dto;

import lombok.Data;

@Data
public class RegisterRequest {
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}
