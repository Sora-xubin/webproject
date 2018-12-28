package com.demo.demo.Entity;



import java.sql.Date;

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
    private String createtime;

    /**
     * user_updatetime
     * 用户信息更新时间
     */
    @Column
    private String  updatetime;

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
    public User() {};

	public User(Integer code, String password, String name, String department, String createtime, String remark) {
		super();
		this.code = code;
		this.password = password;
		this.name = name;
		this.department = department;
		this.createtime = createtime;
		this.remark = remark;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getRolecode() {
		return rolecode;
	}

	public void setRolecode(Integer rolecode) {
		this.rolecode = rolecode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

   
}
