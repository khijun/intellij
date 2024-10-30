package com.example.demo.fileuploadboard.board.dto;

import lombok.Data;

@Data
public class BoardFileDto {
	
	private Long idx;
	
	private Long boardIdx;
	
	private String originalFileName;
	
	private String storedFilePath;
	
	private long fileSize;
}
