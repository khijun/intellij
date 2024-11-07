package edu.du.sb1030.dto;

import lombok.Data;

@Data
public class ChangePwdCommand {
	private String currentPassword;
	private String newPassword;
}
