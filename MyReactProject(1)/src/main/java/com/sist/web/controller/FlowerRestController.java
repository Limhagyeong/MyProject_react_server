package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.el.parser.AstGreaterThanEqual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.FlowerDetailDAO;
import com.sist.web.dao.FlowerListDAO;
import com.sist.web.dao.FlowerStoreDAO;
import com.sist.web.entity.Flowerlist;
import com.sist.web.entity.Flowerstore;
import com.sist.web.entity.Flowersubimg;

@RestController
@CrossOrigin(origins = "*")
public class FlowerRestController {
@Autowired
private FlowerListDAO dao;
@Autowired
private FlowerDetailDAO fdDao;
@Autowired
private FlowerStoreDAO fsDao;

@GetMapping("/flower/list_react/{page}/{cate_minor}")
public ResponseEntity<Map> flowerList(@PathVariable("page") int page,@PathVariable("cate_minor") String cate_minor,@RequestParam("name") String name)
{
	Map map;
	int rowsize=10;
	int start=(page*rowsize)-rowsize;
	final int BLOCK=5;
	int startpage=((page-1)/BLOCK*BLOCK)+1;
	int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
	System.out.println(cate_minor);
	try {
		List<Flowerlist> list;
		List<Flowerlist> list2=dao.flowerHitData(cate_minor);
		int totalpage;
		if(name.isEmpty()) {
			list=dao.flowerListData(start,cate_minor);
			totalpage=dao.totalpage(cate_minor);
		}
		else {
			list=dao.flowerSearchData(start, name, cate_minor);
			totalpage=dao.searchTotalpage(name, cate_minor);
		}
		if(endpage>totalpage)
			   endpage=totalpage; 
		System.out.println(cate_minor);
		map=new HashMap();
		map.put("list",list);
		map.put("list2",list2);
	    map.put("curpage", page);
	    map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("totalpage", totalpage);
	}
	catch(Exception ex) {
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
			
		return new ResponseEntity<>(map,HttpStatus.OK);
}
	

@GetMapping("/flower/detail_react/{fno}")
public ResponseEntity<Map> boardDetail(@PathVariable("fno") int fno)
{

	Flowerlist listdata;
	Map map;
	try {
		if(fno!=0)
		{
			listdata=dao.flowerDetailData(fno);
			listdata.setHit(listdata.getHit() + 1);
		    dao.save(listdata); 
		}
		listdata=dao.flowerDetailData(fno);
		map=new HashMap();
		map.put("list_data",listdata);
		map.put("sub_img", fdDao.findByFno(fno));
		
	}catch(Exception ex) {
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<>(map,HttpStatus.OK);
}

@GetMapping("/flower/store_react")
public List<Flowerstore> flowerStore(int flsno)
{
	// 이미 존재하는 객체이기때문에 새롭게 생성하면 X => hit 외 나머지값은 이미 db에 데이터가 저장되어있기때문임
	if(flsno!=0)
	{
		Flowerstore store = fsDao.findByFlsno(flsno);
		store.setHit(store.getHit() + 1);
	    fsDao.save(store);   
	    List<Flowerstore> list = fsDao.flowerStore();
	    return list;
	}
	
	List<Flowerstore> list = fsDao.flowerStore();
	return list;
}

}
