package com.example.demo.fileuploadboard.board.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardDto {

	private Long boardIdx;
	
	private String title;
	
	private String contents;
	
	private int hitCnt;

	private Long creatorId;
	
	private String createdDatetime;
	
	private Long updaterId;

	private String updatedDatetime;
	
	private List<BoardFileDto> fileList;
}
