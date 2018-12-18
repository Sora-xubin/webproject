package com.demo.demo.Entity;

import java.sql.Date;

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
    private String comment;

    @Column
    private String midreport;

    @Column
    private Date midtime;

    @Column
    private String finreport;

    @Column
    private Date fintime;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMidreport() {
        return midreport;
    }

    public void setMidreport(String midreport) {
        this.midreport = midreport;
    }

    public Date getMidtime() {
        return midtime;
    }

    public void setMidtime(Date midtime) {
        this.midtime = midtime;
    }

    public String getFinreport() {
        return finreport;
    }

    public void setFinreport(String finreport) {
        this.finreport = finreport;
    }

    public Date getFintime() {
        return fintime;
    }

    public void setFintime(Date fintime) {
        this.fintime = fintime;
    }
}
