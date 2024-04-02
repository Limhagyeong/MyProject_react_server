package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Myboard;

public interface BoardDetailDAO extends JpaRepository<Myboard, Integer>{
	public Myboard findByNo(int no);
}
