package com.example.demo2.model.entity;

import com.example.demo2.model.dto.BoardDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude="user")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no; // 글번호
	private String title;  //글제목
	private String content;  // 글내용
	
	@ManyToOne
	@JoinColumn(name="user_id") // Select * from user where id = //
	User user;
	
	public BoardDto toDto() {
		BoardDto dto = new BoardDto();
		dto.setNo(this.getNo());
		dto.setTitle(this.getTitle());
		dto.setContent(this.getContent());
		dto.setUser_Id(this.getUser().getId());
		return dto;
	}
	

}
