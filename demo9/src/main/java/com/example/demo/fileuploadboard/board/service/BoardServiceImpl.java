package com.example.demo.fileuploadboard.board.service;


import com.example.demo.fileuploadboard.board.dto.BoardDto;
import com.example.demo.fileuploadboard.board.dto.BoardFileDto;
import com.example.demo.fileuploadboard.board.mapper.BoardMapper;
import com.example.demo.fileuploadboard.common.FileUtils;
import com.example.demo.fileuploadboard.entity.Board;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.survey.AnsweredData;
import com.example.demo.survey.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

	@PersistenceUnit
	private EntityManagerFactory emf;
	@Autowired
	MemberRepository mr;

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false){
			boardMapper.insertBoardFileList(list);
		}
	}

    @Override
	public BoardDto selectBoardDetail(Long boardIdx) throws Exception{
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
		
		boardMapper.updateHitCount(boardIdx);
		
		return board;
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;

				Member member = mr.findByUsername(((UserDetails) principal).getUsername()).get();
				board.setUpdaterId(member.getId());
//                System.out.println(((UserDetails) principal).getPassword()); 비밀번호 어캐가져옴?ㅋㅋㅅㅂ
			}
		}
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Long boardIdx) throws Exception {
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;

				Member member = mr.findByUsername(((UserDetails) principal).getUsername()).get();
				board.setUpdaterId(member.getId());
//                System.out.println(((UserDetails) principal).getPassword()); 비밀번호 어캐가져옴?ㅋㅋㅅㅂ
			}
		}
		boardMapper.deleteBoard(boardIdx);
	}
	
	@Override
	public BoardFileDto selectBoardFileInformation(Long idx, Long boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
}	

