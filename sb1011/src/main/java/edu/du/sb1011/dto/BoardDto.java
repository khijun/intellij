package edu.du.sb1011.dto;

import lombok.Data;

@Data
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private int creatorId;
	private String createdDatetime;
	private String updaterId;
	private String updatedDatetime;
}