package cn.redarmy.domain;

import java.io.Serializable;

public class Good implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;   
    private String name;   
    private float price;
    
    
	public Good(Integer id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}  
    
    
}
