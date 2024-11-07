package edu.du.sb1030.dto;

import edu.du.sb1030.exception.WrongIdPasswordException;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class Member {

	public Long id;
	public String email;
	public String password;
	public String name;
	public LocalDateTime registerDateTime;

	public Member(String email, String password, 
			String name, LocalDateTime regDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = regDateTime;
	}

    public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new WrongIdPasswordException();
		this.password = newPassword;
	}
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}

}
