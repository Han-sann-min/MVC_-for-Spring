package com.example.demo2.model.dto;

import com.example.demo2.model.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private int no;
	private String title;
	private String content;
	private String user_Id;
	
	public Board toEntity() {
		Board board = new Board();
		board.setNo(this.getNo());
		board.setTitle(this.getTitle());
		board.setContent(this.getContent());
		return board;
	}
	
	
}

