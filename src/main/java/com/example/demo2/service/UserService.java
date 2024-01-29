package com.example.demo2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo2.model.dto.UserDto;
import com.example.demo2.model.entity.Board;
import com.example.demo2.model.entity.User;
import com.example.demo2.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private UserRepository urepo;
	
	@Autowired
	public UserService (UserRepository urepo) {
		this.urepo = urepo;
	}
	
	@Transactional
	public void join(UserDto dto) {
		// TODO Auto-generated method stub
		User user = dto.toEntity();  // dto -> entity 로 변환
		if (urepo.findById(user.getId()).isPresent()) {
			// 이미 존재하는 회원이면 에러 생성
			throw new RuntimeException("이미 존재하는 서비스입니다.");
		}
		
		// 없으면 저장
		urepo.saveAndFlush(user);
	}

	public UserDto login(UserDto dto) {
		// TODO Auto-generated method stub
		Optional<User> result = urepo.findById(dto.getId()); // isPresent() 
		
		if (result.isPresent()) {
			User selected = result.get();
			if (selected.getPass().equals(dto.getPass())) {
				return selected.toDto();
			}
		}
		
		throw new RuntimeException("아이디나 비밀번호를 확인하세요.");
		
	}

}
