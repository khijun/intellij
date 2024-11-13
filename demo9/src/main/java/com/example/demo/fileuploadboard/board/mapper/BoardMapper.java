package com.example.demo.fileuploadboard.board.mapper;


import com.example.demo.fileuploadboard.board.dto.BoardDto;
import com.example.demo.fileuploadboard.board.dto.BoardFileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList() throws Exception;
	
	void insertBoard(BoardDto board) throws Exception;

	BoardDto selectBoardDetail(Long boardIdx) throws Exception;

	void updateHitCount(Long boardIdx) throws Exception;
	
	void updateBoard(BoardDto board) throws Exception;
	
	void deleteBoard(Long boardIdx) throws Exception;

	void insertBoardFileList(List<BoardFileDto> list) throws Exception;

	List<BoardFileDto> selectBoardFileList(Long boardIdx) throws Exception;

	BoardFileDto selectBoardFileInformation(@Param("idx") Long idx, @Param("boardIdx" ) Long boardIdx);
}