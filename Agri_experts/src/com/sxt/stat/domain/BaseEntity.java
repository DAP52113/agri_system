package com.sxt.stat.domain;

public class BaseEntity {

	
	private String name;
	private Double value;
	
	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseEntity(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
