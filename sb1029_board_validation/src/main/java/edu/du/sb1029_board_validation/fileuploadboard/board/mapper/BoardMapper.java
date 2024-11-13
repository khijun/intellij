package edu.du.sb1029_board_validation.fileuploadboard.board.mapper;

import edu.du.sb1029_board_validation.fileuploadboard.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList() throws Exception;
	
	void insertBoard(BoardDto board) throws Exception;

	BoardDto selectBoardDetail(int boardIdx) throws Exception;

	void updateHitCount(int boardIdx) throws Exception;
	
	void updateBoard(BoardDto board) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;

//	void insertBoardFileList(List<BoardFileDto> list) throws Exception;

//	List<BoardFileDto> selectBoardFileList(int boardIdx) throws Exception;

//	BoardFileDto selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx" )int boardIdx);
}