package edu.du.sb1014_2.controller;

import edu.du.sb1014_2.entity.Board;
import edu.du.sb1014_2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardList");
		mv.addObject("list", boardRepository.findAll());
		
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(Board board) throws Exception{
		boardRepository.save(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam Integer boardIdx) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		mv.addObject("board", boardRepository.findById(boardIdx));

		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(Board board) throws Exception{
		boardRepository.save(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(Integer boardIdx) throws Exception{
		boardRepository.deleteById(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
}
