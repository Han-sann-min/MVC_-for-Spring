package com.example.demo2.service;


import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo2.model.dto.BoardDto;
import com.example.demo2.model.entity.Board;
import com.example.demo2.model.entity.User;
import com.example.demo2.repository.BoardRepository;
import com.example.demo2.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
	private BoardRepository brepo;
	private UserRepository urepo;
	
	//controller 에서 값을 받아올 생성자를 만듭니다.
	@Autowired
	public BoardService(BoardRepository brepo, UserRepository urepo) {
		this.brepo = brepo;
		this.urepo = urepo;
		
	}
	
	// 쓰기
	@Transactional // 아래의 작업이 순차적으로 모두 완료 되었을 때만 테이블에 글이 들어간다.
	public void writeBoard(BoardDto dto) {
		String userId = dto.getUser_Id();
		User user = urepo.getReferenceById(userId);
		Board board = dto.toEntity();
		
		board.setUser(user);
		brepo.saveAndFlush(board);
	}
	
	// 읽기
	
	// 전체 글 읽기
	public Page <Board> listBoard(int page){
		Pageable pageable = PageRequest.of(page, 5, Direction.DESC, "no");
		Page <Board> pageInfo = brepo.findAll(pageable);
		return pageInfo;
	}
	
	// no = {글번호} 로 특정 글 읽기
	public Board detailBoard(int no) {
		
		Optional<Board> option = brepo.findById(no); // isPresent() 
		
		if (option.isPresent()) {
			return option.get();			
		}
		else {
			throw new RuntimeException(no + "라는 글은 없습니다.");
		}
	}
	
	
	// 수정
	// deleteBoard
	
	// 삭제
	public void deleteBoard(int no) {
		brepo.deleteById(no);
	}

}