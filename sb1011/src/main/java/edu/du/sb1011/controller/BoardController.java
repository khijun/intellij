package edu.du.sb1011.controller;

import edu.du.sb1011.dto.BoardDto;
import edu.du.sb1011.dto.MemberDto;
import edu.du.sb1011.exception.InvalidBoardException;
import edu.du.sb1011.exception.NotLoginedException;
import edu.du.sb1011.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String index(){
		return "redirect:/board/openBoardList.do";
	}
	
	@GetMapping("/board/openBoardList.do")
	public String openBoardList(Model model) throws Exception{
		List<BoardDto> list = boardService.selectBoardList();
		model.addAttribute("list", list);
		return "/board/boardList";
	}
	
	@GetMapping("/board/openBoardWrite.do")
	public String openBoardWrite(Model model, HttpServletRequest request) throws Exception{
		try {
			MemberDto member = (MemberDto)request.getSession().getAttribute(("member"));
			if(member == null)
				throw new NotLoginedException();
			model.addAttribute("member", member);
			return "board/boardWrite";
		}catch(NotLoginedException e){
			e.printStackTrace();
			model.addAttribute("msg", e.getClass().getName());// 임시 예외처리
			return "board/boardError.html";// 오류페이지
		}
	}
	
	@PostMapping("/board/insertBoard.do")
	public String insertBoard(Model model, BoardDto board) throws Exception{
		try{
			boardService.insertBoard(board);//	보드에 필요한 뭔가가 없음
			return "redirect:/board/openBoardList.do";
		}catch(InvalidBoardException e){
			e.printStackTrace();
			model.addAttribute("msg", e.getClass().getName());// 임시 예외처리
			return "board/boardError.html";// 오류페이지
		}
	}
	
	@GetMapping("/board/openBoardDetail.do")
	public String openBoardDetail(@RequestParam int boardIdx, Model model) throws Exception{
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		model.addAttribute("board", board);
		
		return "/board/boardDetail";
	}
	
	@PostMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@PostMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
}
