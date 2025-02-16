package com.sheryians.major.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity

public class Category {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String name;
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Category( String name,int id) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
		super();
	}
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}