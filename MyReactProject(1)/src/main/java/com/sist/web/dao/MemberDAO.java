package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.Promember;

public interface MemberDAO extends JpaRepository<Promember, Integer>{
	@Query(value = "SELECT COUNT(*) FROM promember WHERE id=:id",nativeQuery = true)
	public int idCount(@Param("id") String id);
	   
	public Promember findById(String id);
	
}
