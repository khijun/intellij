package edu.du.sb1011.service;

import edu.du.sb1011.dto.BoardDto;
import edu.du.sb1011.exception.InvalidBoardException;
import edu.du.sb1011.mapper.BoardMapper;
import edu.du.sb1011.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private MemberMapper mm;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board) throws InvalidBoardException, Exception {
		if(board.getContents().isEmpty()||
			board.getTitle().isEmpty()||
			mm.getOneByIdx(board.getCreatorId())==null) //	필요할진 잘 모르겠음.
			throw new InvalidBoardException();
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception{
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		boardMapper.updateHitCount(boardIdx);
		
		return board;
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
}	

