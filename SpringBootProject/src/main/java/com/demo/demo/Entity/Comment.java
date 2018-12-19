package com.demo.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by XB on 2018/12/19.
 */
@Entity
@Table(name = "comment")
public class Comment extends EntityBase{
    @Column
    private int projectcode;

    @Column
    private int expertcode;

    @Column
    private int mark;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Column
    private String coment;

    public int getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(int projectcode) {
        this.projectcode = projectcode;
    }

    public int getExpertcode() {
        return expertcode;
    }

    public void setExpertcode(int expertcode) {
        this.expertcode = expertcode;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}
