package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BoardDAO;
import com.sist.web.dao.BoardDetailDAO;
import com.sist.web.entity.BoardVO;
import com.sist.web.entity.Myboard;

import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class BoardRestController {
@Autowired
private BoardDAO bDao;
@Autowired
private BoardDetailDAO bdDao;

@GetMapping("/board/list_myboard/{page}")
public ResponseEntity<Map> boardListData(@PathVariable("page") int page)
{
	  Map map=new HashMap();
	  try
	  {
		  int rowsize=10;
		  int start=(rowsize*page)-rowsize; 
		  List<Myboard> list=bDao.boardListData(start);
		  int totalpage=(int)(Math.ceil(bDao.count()/10.0));
		  map.put("bList", list);
		  map.put("totalpage", totalpage);
		  map.put("curpage", page);
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(map,HttpStatus.OK);
}

@GetMapping("/board/detail_myboard/{no}")
public ResponseEntity<Myboard> boardDetailData(@PathVariable("no") int no)
{
	    Myboard board=null;
	    try
	    {
	    	board=bDao.findByNo(no);
	    	board.setHit(board.getHit()+1);
	    	bDao.save(board);
	    	board=bDao.findByNo(no);
	    }catch(Exception ex)
	    {
	    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return new ResponseEntity<>(board,HttpStatus.OK);
}

@PostMapping("/board/insert_myboard")
public ResponseEntity<Map> boardInsert(@RequestBody Myboard board)
{
	  Map map=new HashMap();
	  try
	  {
		  Myboard insert=bDao.save(board);
		  map.put("board", insert);
		  map.put("msg", "yes");
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(map,HttpStatus.CREATED);
}

@GetMapping("/board/update_myboard/{no}")
public ResponseEntity<Myboard> boardUpdateData(@PathVariable("no") int no)
{
	  Myboard board=null;
	  try
	  {
		  board=bDao.findByNo(no);
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(board,HttpStatus.OK);
}

@PutMapping("/board/update_ok_myboard/{no}")
public ResponseEntity<Map> boardUpDataOk(@PathVariable("no") int no, @RequestBody Myboard board)
{
	  Map map=new HashMap();
	  try
	  {
		  Myboard dbBoard=bDao.findByNo(no);
		  if(dbBoard.getPwd().equals(board.getPwd()))
		  {
			  board.setNo(no);
			  board.setHit(dbBoard.getHit());
			  Myboard b=bDao.save(board);
			  map.put("board", b);
			  map.put("msg", "yes");
		  }
		  else
		  {
			  map.put("msg", "no");
		  }
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	  return new ResponseEntity<>(map,HttpStatus.OK);
}

@DeleteMapping("/board/delete_myboard/{no}/{pwd}")
public ResponseEntity<Map> boardDelete(@PathVariable("no") int no, @PathVariable("pwd") String pwd)
{
	  Map map=new HashMap();
	  try
	  {
		  Myboard board=bDao.findByNo(no);
		  if(pwd.equals(board.getPwd()))
		  {
			  bDao.delete(board);
			  map.put("msg", "yes");
		  }
		  else
		  {
			  map.put("msg", "no");
		  }
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(map,HttpStatus.OK);
}
}