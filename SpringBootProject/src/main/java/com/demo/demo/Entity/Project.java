package com.demo.demo.Entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project extends EntityBase{
    @Column
    private int code;

    @Column
    private String name;

    @Column
    private String level;

    @Column
    private String promise;

    @Column
    private int state;

    @Column
    private String midreport;

    @Column
    private Date midtime;

    @Column
    private String midexplain;

    @Column
    private String finreport;

    @Column
    private Timestamp fintime;

    @Column
    private String finexplain;

    public String getMidexplain() {
        return midexplain;
    }

    public void setMidexplain(String midexplain) {
        this.midexplain = midexplain;
    }

    public String getFinexplain() {
        return finexplain;
    }

    public void setFinexplain(String finexplain) {
        this.finexplain = finexplain;
    }

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMidreport() {
        return midreport;
    }

    public Date getMidtime() {
		return midtime;
	}

	public void setMidtime(Date midtime) {
		this.midtime = midtime;
	}

	public void setMidreport(String midreport) {
        this.midreport = midreport;
    }

    public String getFinreport() {
        return finreport;
    }

    public void setFinreport(String finreport) {
        this.finreport = finreport;
    }

    public Timestamp getFintime() {
        return fintime;
    }

    public void setFintime(Timestamp fintime) {
        this.fintime = fintime;
    }
}
