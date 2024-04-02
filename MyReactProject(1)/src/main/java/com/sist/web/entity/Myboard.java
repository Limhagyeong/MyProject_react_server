package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * no int AI PK 
name varchar(51) 
subject varchar(1000) 
content text 
pwd varchar(10) 
regdate datetime 
hit int
 */
@Entity
@Data
@DynamicUpdate
@NoArgsConstructor
public class Myboard {
	@Id
	private int no;
	private String subject;
	private String name;
	private String content;
	@Column(insertable = true, updatable = false)
	private String pwd;
	private int hit;
	@Column(insertable = true, updatable = false)
	private String regdate;
	
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	}
	
	  public Myboard(String name, String subject, String content, String pwd) {
			super();
			this.name = name;
			this.subject = subject;
			this.content = content;
			this.pwd = pwd;
		  }
}
