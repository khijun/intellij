package edu.du.sb1029_board_validation.fileuploadboard.board.service;

import edu.du.sb1029_board_validation.fileuploadboard.board.dto.BoardDto;
import edu.du.sb1029_board_validation.fileuploadboard.board.mapper.BoardMapper;
import edu.du.sb1029_board_validation.fileuploadboard.entity.Board;
import edu.du.sb1029_board_validation.fileuploadboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
//	@Autowired
//	private FileUtils fileUtils;

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public List<Board> selectBoardList(Pageable pageable) throws Exception {
		return boardRepository.findAllByOrderByBoardIdxDesc(pageable);
	}

	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);
//		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
//		if(CollectionUtils.isEmpty(list) == false){
//			boardMapper.insertBoardFileList(list);
//		}
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception{
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
//		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
//		board.setFileList(fileList);
		
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
	
//	@Override
//	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception {
//		return boardMapper.selectBoardFileInformation(idx, boardIdx);
//	}
}	

