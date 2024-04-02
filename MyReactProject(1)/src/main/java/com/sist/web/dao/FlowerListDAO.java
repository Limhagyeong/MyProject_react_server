package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Flowerlist;
public interface FlowerListDAO extends JpaRepository<Flowerlist, Integer>{

	
	// 카테고리 나누기
	@Query(value = "SELECT * FROM FlowerList WHERE cate_minor LIKE CONCAT ('%',:cate_minor,'%') ORDER BY fno ASC LIMIT :start,12",nativeQuery = true)
	public List<Flowerlist> flowerListData(@Param("start") int start,@Param("cate_minor") String cate_minor);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12) FROM FlowerList WHERE cate_minor LIKE CONCAT ('%',:cate_minor,'%') ",nativeQuery = true)
	public int totalpage(@Param("cate_minor") String cate_minor);
	
	@Query(value = "SELECT * FROM FlowerList WHERE fno =:fno",nativeQuery = true)
	public Flowerlist flowerDetailData(@Param("fno") int fno);
	
	// 검색어 
	@Query(value = "SELECT * FROM FlowerList WHERE name LIKE CONCAT ('%',:name,'%') AND cate_minor LIKE CONCAT ('%',:cate_minor,'%') ORDER BY fno ASC LIMIT :start,12",nativeQuery = true)
	public List<Flowerlist> flowerSearchData(@Param("start") int start,@Param("name") String name,@Param("cate_minor") String cate_minor);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12) FROM FlowerList WHERE name LIKE CONCAT ('%',:name,'%') AND cate_minor LIKE CONCAT ('%',:cate_minor,'%')",nativeQuery = true)
	public int searchTotalpage(@Param("name") String name,@Param("cate_minor") String cate_minor);
	
	@Query(value = "SELECT * FROM FlowerList WHERE cate_minor LIKE CONCAT ('%',:cate_minor,'%') ORDER BY hit DESC LIMIT 0,4",nativeQuery = true)
	public List<Flowerlist> flowerHitData(@Param("cate_minor") String cate_minor);


	
}
