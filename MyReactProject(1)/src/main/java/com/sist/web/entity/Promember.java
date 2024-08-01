package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * no int primary key auto_increment,
	id varchar(20),
    pwd varchar(20),
    name varchar(50) not null,
    gender char(1) check (gender IN ('M', 'F')),
    bday date,
    addr varchar(200),
    phone varchar(20),
    email varchar(200)
 */

@Entity
@Data
public class Promember {
	@Id
	private int no;
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String bday;
	private String addr;
	private String phone;
	private String email;
	private String addrdetail;
}
