package com.demo.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends EntityBase{
    @Column
    private int code;

    @Column
    private String name;
    
    @Column
    private String describe;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
    
}
