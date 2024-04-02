package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sist.web.entity.Flowerstore;

public interface FlowerStoreDAO  extends JpaRepository<Flowerstore, Integer>{
	@Query(value =  "SELECT * FROM flowerstore WHERE address IS NOT null ORDER BY flsno ASC", nativeQuery = true)
	public List<Flowerstore> flowerStore();
	
	public Flowerstore findByFlsno(int flsno);

}