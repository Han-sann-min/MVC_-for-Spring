package com.example.demo2.model.entity;

import java.util.List;

import com.example.demo2.model.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // h2 database 에 user 라는 테이블을 만들었떠니 오류가 발생했지만 여기는 괜찮
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude="boards")
public class User {
	@Id
	private String id; // 글번호 -> UserId
	private String name;  // user 이름
	private String pass;  // 비번
	private String grade; // 등급

	// Board 와 어떻게 맺어져야 하는가...
	
	@OneToMany(mappedBy = "user")
	List <Board> boards; 
	
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setId(this.getId());
		dto.setName(this.getName());
		dto.setPass(this.getPass());
		dto.setGrade(this.getGrade());
		
		return dto;
	}

}
