package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sist.web.entity.Myboard;

import java.util.*;
public interface BoardDAO extends JpaRepository<Myboard, Integer>{
	@Query(value =  "SELECT * FROM myboard ORDER BY no DESC LIMIT :start,10", nativeQuery = true)
	public List<Myboard> boardListData(@Param("start") int start);
	
	public Myboard findByNo(int no);
}
