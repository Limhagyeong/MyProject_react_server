package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * 	fno NUMBER,
    name varchar2(250) not null,
    img varchar2(250) not null,
    category varchar2(150) not null,
    cate_minor varchar2(150) not null,
    price varchar2(250) not null,
    int_price number not null,
 */
@Entity
@Data
public class Flowerlist {
	@Id
	public int fno;
	public int int_price;
	public int hit;
	public String name,img,category,cate_minor,price;
}
