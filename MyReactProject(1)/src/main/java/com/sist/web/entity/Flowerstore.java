package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Flowerstore {
	@Id
	public int flsno;
	public String name;
	public String img;
	public String address;
	public String phone;
	public int hit;
}
