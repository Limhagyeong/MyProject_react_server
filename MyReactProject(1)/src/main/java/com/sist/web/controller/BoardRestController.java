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
import com.sist.web.service.BoardService;

import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class BoardRestController {
	
@Autowired
private BoardService bService;

@GetMapping("/board/list_myboard/{page}")
public ResponseEntity<Map> boardListData(@PathVariable("page") int page)
{
	  Map map=new HashMap();
	  try
	  {
		  int rowsize = 10;
          List<Myboard> list = bService.getBoardList(page, rowsize);
          int totalpage = bService.getTotalPages(rowsize);
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
		try
	    {
			 Myboard board=bService.getboardDetail(no);
	         if (board!=null) {
	        	 System.out.println(board.getHit());
	        	 return new ResponseEntity<>(board, HttpStatus.OK);
	         }
	         else {
	        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	         }
	    }
	    catch(Exception ex)
	    {
	    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}

@PostMapping("/board/insert_myboard")
public ResponseEntity<Map> boardInsert(@RequestBody Myboard board)
{
	  Map map=new HashMap();
	  try
	  {
		  Myboard insert=bService.boardInsert(board);
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
	  try
	  {
		Myboard  board=bService.boardUpdate(no);
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(null,HttpStatus.OK);
}
//
@PutMapping("/board/update_ok_myboard/{no}")
public ResponseEntity<Map> boardUpDataOk(@PathVariable("no") int no, @RequestBody Myboard board)
{
	  Map map=new HashMap();
	  try
	  {
		  Map result=bService.updateBoard(no, board);
          if ("yes".equals(result.get("msg"))) {
              return new ResponseEntity<>(result, HttpStatus.OK);
          } else {
              return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
          }
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
}

@DeleteMapping("/board/delete_myboard/{no}/{pwd}")
public ResponseEntity<Map> boardDelete(@PathVariable("no") int no, @PathVariable("pwd") String pwd)
{
	  Map map=new HashMap();
	  try
	  {
		  Map result=bService.boardDelete(no, pwd);
		  if("yes".equals(result.get("msg")))
		  {
			  return new ResponseEntity<>(result,HttpStatus.OK);
		  }
		  else
		  {
			  return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		  }
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
}