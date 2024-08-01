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
import com.sist.web.service.FlowerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlowerRestController {

@Autowired
private FlowerService fService;

@GetMapping("/flower/list_react/{page}")
public ResponseEntity<Map> flowerList(@PathVariable("page") int page,@RequestParam("cate_minor") String cate_minor,@RequestParam("name") String name)
{
	System.out.println("실행");
	
	try {
		Map map=fService.getFlowerList(page, cate_minor, name);
        return new ResponseEntity<>(map, HttpStatus.OK);
	}
	catch(Exception ex) {
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	

@GetMapping("/flower/detail_react/{fno}")
public ResponseEntity<Map> boardDetail(@PathVariable("fno") int fno)
{

	try {
		Map map= fService.getFlowerDeatil(fno);
        return new ResponseEntity<>(map, HttpStatus.OK);
		
	}catch(Exception ex) {
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@GetMapping("/flower/store_react")
public List<Flowerstore> flowerStore(int flsno)
{
	List<Flowerstore> list=fService.flowerStore(flsno);
	return list;
}

}
