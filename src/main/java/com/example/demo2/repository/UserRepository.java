package com.example.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo2.model.entity.User;


public interface UserRepository extends JpaRepository<User, String> {

}
