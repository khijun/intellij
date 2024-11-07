package edu.du.sb1030.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class AuthInfo {

	private Long id;
	private String email;
	private String name;


}
