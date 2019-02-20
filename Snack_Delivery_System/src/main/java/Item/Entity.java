package Snack_Delivery_System;

import java.io.Serializable;
import java.util.Date;

abstract public class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected String name;
	protected Date dateCreated;
	
	public Entity(){
		this(0,"");
	}
	
	public Entity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+ "[ID: " + id + ", Name:" + name + ", Date Created:" 
				+ dateCreated + "]"; 
	}
	
	
	
	
}
