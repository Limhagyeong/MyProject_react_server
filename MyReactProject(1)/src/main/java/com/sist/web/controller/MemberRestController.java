package com.sist.web.controller;
import com.sist.web.entity.*;
import com.sist.web.service.MemberService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;

import java.net.URLEncoder;
import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class MemberRestController {
   @Autowired
   private MemberService mService;
   
   @PostMapping("/member/signup")
   public ResponseEntity<Map> memberSignup(@RequestBody Promember member) {
       try {
           Map map = mService.memberSignup(member);
           return new ResponseEntity<>(map, HttpStatus.OK);
       } catch (Exception ex) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
  
   @PostMapping("/member/login/{id}/{pwd}")
   public ResponseEntity<Map> memberLogin(@PathVariable("id") String id,@PathVariable("pwd") String pwd, HttpSession session)
   {
	   try
	   {
		   Map map=mService.memberLogin(id, pwd, session);
		   System.out.println(session.getAttribute("id"));
		   return new ResponseEntity<>(map,HttpStatus.OK);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
   }
   
   @PostMapping("/member/logout")
   public ResponseEntity<String> memberLogout(HttpSession session) {
       try {
           session.invalidate();
           return new ResponseEntity<>("Logout successful", HttpStatus.OK);
       } catch (Exception ex) {
           return new ResponseEntity<>("Logout failed", HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
}