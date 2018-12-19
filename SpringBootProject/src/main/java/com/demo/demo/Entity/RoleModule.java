package com.demo.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="rolemodule")
public class RoleModule extends EntityBase{
	
	@Column
	private Integer roleid;
	@Column
	private Integer moduleid;
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getModuleid() {
		return moduleid;
	}
	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}
	
	

}
