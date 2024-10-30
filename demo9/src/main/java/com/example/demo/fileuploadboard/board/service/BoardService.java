package com.example.demo.fileuploadboard.board.service;


import com.example.demo.fileuploadboard.board.dto.BoardDto;
import com.example.demo.fileuploadboard.board.dto.BoardFileDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
	
	List<BoardDto> selectBoardList() throws Exception;
	
	void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

	BoardDto selectBoardDetail(Long boardIdx) throws Exception;

	void updateBoard(BoardDto board) throws Exception;

	void deleteBoard(Long boardIdx) throws Exception;

	BoardFileDto selectBoardFileInformation(Long idx, Long boardIdx) throws Exception;
}
