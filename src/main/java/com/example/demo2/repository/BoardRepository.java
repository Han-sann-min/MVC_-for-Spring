package com.example.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo2.model.entity.Board;



public interface BoardRepository extends JpaRepository<Board, Integer> {

}
