package com.sist.web.controller;
import com.sist.web.entity.*;
import com.sist.web.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class MemberRestController {
   @Autowired
   private MemberService mService;
  
   @GetMapping("/member/login/{id}/{pwd}")
   public ResponseEntity<Map> memberLogin(@PathVariable("id") String id,@PathVariable("pwd") String pwd)
   {
	   try
	   {
		   Map map=mService.memberLogin(id, pwd);
		   return new ResponseEntity<>(map,HttpStatus.OK);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
   }
}