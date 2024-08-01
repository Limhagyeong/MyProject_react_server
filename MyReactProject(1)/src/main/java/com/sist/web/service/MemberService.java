package com.sist.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sist.web.dao.MemberDAO;
import com.sist.web.entity.Myboard;
import com.sist.web.entity.Promember;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO mDao;
	
	public Map memberSignup(Promember member) {
        Map map = new HashMap();
        try {
            	int count=mDao.countById(member.getId());
            	if (count>0) {
                map.put("msg", "EXIST");
            	} 
            	else {
                mDao.save(member);
                map.put("msg", "OK");
            	}
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            map.put("msg", "ERROR");
        }
        return map;
    }
	
	public Map memberLogin(String id, String pwd,HttpSession session) {
		Map map=new HashMap();
		try
		   {
			   int count=mDao.idCount(id);
			   if(count==0)
			   {
				   map.put("msg", "NOID");
			   }
			   else
			   {
				   Promember mem=mDao.findById(id);
				   if(pwd.equals(mem.getPwd()))
				   {
					   map.put("name", mem.getName());
					   map.put("id", mem.getId());
					   map.put("msg", "OK");
					   
					   session.setAttribute("id", mem.getId());
					   session.setAttribute("pwd", mem.getPwd());
				   }
				   else
				   {
					  map.put("msg", "NOPWD"); 
				   }
			   }
		   }catch(Exception ex) {}
		return map;
	}
}
