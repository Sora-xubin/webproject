package com.demo.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="module")
public class Module extends EntityBase{
	
	@Column
	private String modulename;

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	
	

}
