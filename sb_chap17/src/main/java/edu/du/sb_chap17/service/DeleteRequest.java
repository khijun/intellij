package edu.du.sb_chap17.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteRequest {
	private int articleId;
	private String password;

}
