package edu.du.sb1029_board_validation.fileuploadboard.board.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class BoardDto {
	
	private int boardIdx;
	@NotBlank
	private String title;
	@NotBlank
	private String contents;
	
	private int hitCnt;
	
	private String creatorId;
	
	private String createdDatetime;
	
	private String updaterId;
	
	private String updatedDatetime;
	
//	private List<BoardFileDto> fileList;
}
