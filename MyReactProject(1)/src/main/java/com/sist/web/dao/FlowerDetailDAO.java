package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Flowersubimg;

public interface FlowerDetailDAO extends JpaRepository<Flowersubimg, Integer>{
	public List<Flowersubimg> findByFno(int fno);

}
