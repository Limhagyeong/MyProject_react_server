package com.sist.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sist.web.dao.BoardDAO;
import com.sist.web.dao.BoardDetailDAO;
import com.sist.web.entity.Myboard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bDao;
	@Autowired
	private BoardDetailDAO bdDao;
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Myboard> getBoardList(int page, int rowsize) {
        int start = (rowsize*page)-rowsize;
        return bDao.boardListData(start);
    } 

    public int getTotalPages(int rowsize) {
        return (int)(Math.ceil(bDao.count()/10.0));
    }
    
    public Myboard getboardDetail(int no) {
    	Myboard board = bDao.findByNo(no);
        if (board!=null) {
            board.setHit(board.getHit() + 1);
            bDao.save(board);
            board=bDao.findByNo(no);
            System.out.println("저장완료");
        }
        return board;
    }
    
    public Myboard boardInsert(Myboard board) {
    	return bDao.save(board);
    }
    
    public Myboard boardUpdate(int no) {
    	return bDao.findByNo(no);
    }
    
    public Map updateBoard(int no, Myboard board) {
        Map map = new HashMap();
        Myboard dbBoard = bDao.findByNo(no);
        if (dbBoard.getPwd().equals(board.getPwd())) {
            board.setNo(no);
            board.setHit(dbBoard.getHit());
            Myboard updatedBoard = bDao.save(board);
            map.put("board", updatedBoard);
            map.put("msg", "yes");
        } else {
            map.put("msg", "no");
        }
        return map;
    }
    
    public Map boardDelete(int no, String pwd) {
    	Map map=new HashMap();
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
		 return map;
    }
}
