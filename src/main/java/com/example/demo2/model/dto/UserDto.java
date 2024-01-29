package com.example.demo2.model.dto;

import com.example.demo2.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String id; // 글번호 -> UserId
	private String name;  // user 이름
	private String pass;  // 비번
	private String grade; // 등급

	public User toEntity() {
		User user = new User();
		user.setId(this.getId());
		user.setName(this.getName());
		user.setPass(this.getPass());
		user.setGrade(this.getGrade());
		
		return user;
	}
}
