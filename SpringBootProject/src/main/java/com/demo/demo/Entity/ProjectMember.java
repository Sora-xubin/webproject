package com.demo.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project_member")
public class ProjectMember extends EntityBase {
    @Column
    private int projectcode;

    @Column
    private int usercode;

    @Column
    private String identity;

    @Column
    private String email;

    public int getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(int projectcode) {
        this.projectcode = projectcode;
    }

    public int getUsercode() {
        return usercode;
    }

    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
