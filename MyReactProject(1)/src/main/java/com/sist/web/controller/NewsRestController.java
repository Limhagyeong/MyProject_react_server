package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.manager.NewsManager;

@RestController
@CrossOrigin(origins = "*")
public class NewsRestController {
@Autowired
private NewsManager mgr;

@GetMapping("/news/list_react")
public String newsList(String fd)
{
	return mgr.newsFindData(fd);
}


}