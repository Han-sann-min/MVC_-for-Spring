package com.example.demo2.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo2.model.dto.BoardDto;
import com.example.demo2.model.entity.Board;
import com.example.demo2.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService service;
	
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	// 1. 게시글 등록 페이지로 이동 - 글쓰기 화면으로 이독만 하면 됌
	@GetMapping("/regist")
	public String registForm() {
		return "board/registboard";
	}
	
	// 2. 글을 Form 으로 받아서 전달
	@PostMapping("/regist") 
	public String registFrom(@ModelAttribute BoardDto board) {
		service.writeBoard(board);
		return "redirect:/board/list";
	}
	
	// 3. 목록을 출력하는 메서드
	@GetMapping("/list")
	public String list(@RequestParam(required = false, defaultValue =  "1") Integer page, Model model) { 
		page--;
		Page<Board> pageInfo = service.listBoard(page);
		model.addAttribute("pageInfo", pageInfo);
		
		//log.debug("page: {}",page);
		return "board/list";

	}
	
	// 4. 글 하나를 출력하는 메서드
	@GetMapping("/detail")
	public String detail(@RequestParam int no, Model model) {
		try {
			Board board = service.detailBoard(no);
			model.addAttribute("board", board);
			return "board/detail";
		}
		catch (RuntimeException e) {
			// TODO: handle exception
			return "board/list";
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int no) {
	    try {
	        service.deleteBoard(no);
	        return "redirect:/board/list";
	    } catch (RuntimeException e) {
	        // 예외 처리 - 삭제에 실패한 경우
	        return "board/list";
	    }
	}
	
}
