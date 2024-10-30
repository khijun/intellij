package com.example.demo.fileuploadboard.board.service;


import com.example.demo.fileuploadboard.board.mapper.BoardMapper;
import com.example.demo.fileuploadboard.common.FileUtils;
import com.example.demo.fileuploadboard.entity.Board;
import com.example.demo.fileuploadboard.entity.BoardFile;
import com.example.demo.member.entity.Member;
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
public class BoardServiceImpl{

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Autowired
	private EntityManager em;

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	public List<Board> selectBoardList() throws Exception {
		return em.createQuery("select b from Board b", Board.class).getResultList();
	}
	
	public void insertBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(board);
		List<BoardFile> list = fileUtils.parseFileInfo(board, multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false){
			em.persist(list);


			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication != null && authentication.isAuthenticated()) {
				Object principal = authentication.getPrincipal();

				if (principal instanceof UserDetails) {
					UserDetails userDetails = (UserDetails) principal;

					Member member = em.createQuery("select m from Member m where username = :username",Member.class)
							.setParameter("username", ((UserDetails) principal).getUsername()).getSingleResult();
//                System.out.println(((UserDetails) principal).getPassword()); 비밀번호 어캐가져옴?ㅋㅋㅅㅂ
					if(member != null
//                        &&(member.getPassword().equals(((UserDetails) principal).getPassword()))
					)
						board.setMember(member);
				}
			}

			tx.commit();
		}
	}

	public Board selectBoardDetail(int boardIdx) throws Exception{
		Board board = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFile> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
		
		boardMapper.updateHitCount(boardIdx);
		
		return board;
	}
	
	public void updateBoard(Board board) throws Exception {
		boardMapper.updateBoard(board);
	}

	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
	
	public BoardFile selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return em.createQuery("select b from BoardFile b where board = :boardId").setParameter("boardId", boardIdx).getSingleResult();
	}
}	

