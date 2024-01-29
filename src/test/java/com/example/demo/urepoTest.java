package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo2.model.entity.User;
import com.example.demo2.repository.UserRepository;




//DB와 JPA에 관한 어노테이션을 넣어줍니다
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class urepoTest {
	
	@Autowired
	UserRepository urepo;
	
	@Test
	public void insertTest() {
		// given   ID, NAME, PASS, GRADE, LIST<board>
//		User user = new User("test", "김연지", "1234", "GUEST");
		User user = User.builder().id("test").name("신짱구").pass("1234").build();
		urepo.saveAndFlush(user);
		
		// when - 실제로 동작해야 할 상태
		User selected = urepo.findById("test").get();
		System.out.println(selected);
		
		// then 
		Assertions.assertEquals(user, selected, "둘이 다름");
		
	}
}