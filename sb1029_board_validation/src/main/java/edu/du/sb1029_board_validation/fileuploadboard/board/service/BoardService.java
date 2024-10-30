package edu.du.sb1029_board_validation.fileuploadboard.board.service;

import edu.du.sb1029_board_validation.fileuploadboard.board.dto.BoardDto;
import edu.du.sb1029_board_validation.fileuploadboard.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
	
	List<BoardDto> selectBoardList() throws Exception;

	List<Board> selectBoardList(Pageable pageable) throws Exception;
	
	void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

	BoardDto selectBoardDetail(int boardIdx) throws Exception;

	void updateBoard(BoardDto board) throws Exception;

	void deleteBoard(int boardIdx) throws Exception;

//	BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception;
}
