package com.demo.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends EntityBase{


    /**
     * user_code
     * 用户编号
     */
    @Column
    private Integer code;

    /**
     * user_password
     * 用户密码
     */
    @Column
    private String password;

    /**
     * user_name
     * 用户姓名
     */
    @Column
    private String name;

    /**
     * user_department
     * 用户专业
     */
    @Column
    private String department;

    /**
     * user_creattime
     * 用户创建时间
     */
    @Column
    private String creattime;

    /**
     * user_updatetime
     * 用户信息更新时间
     */
    @Column
    private String updatetime;

    /**
     * user_state
     * 用户状态
     */
    @Column
    private String state;

    /**
     * user_rolecode
     * 用户角色编码
     */
    @Column
    private Integer rolecode;

    /**
     * user_remark
     * 用户备注
     */
    @Column
    private String remark;

    public User(){}
    public void setCode(int code) {
        this.code = code;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRolecode(int rolecode) {
        this.rolecode = rolecode;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCode() {

        return code;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getCreattime() {
        return creattime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public String getState() {
        return state;
    }

    public int getRolecode() {
        return rolecode;
    }

    public String getRemark() {
        return remark;
    }
}
