package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
 * 	fsno NUMBER,
	fno NUMBER,
    sub_img varchar2(250) not NULL,
 */
@Entity
@Data
public class Flowersubimg {
	@Id
	public int fsno;
	public int fno;
	public String sub_img;
}
