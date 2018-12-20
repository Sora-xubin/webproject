package com.demo.demo.Dao;

import com.demo.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserDao extends JpaRepository<User,Integer> {
   
	public String findPasswordByCode(Integer code);
    
	public Boolean findAllByCode(Integer code);
	
	public Integer findIdByCode(Integer code);
	
	@Modifying
	@Query("update User u set u.name=:name,u.department=:department,"
			+ "u.updatetime=:updatetime,u.state=:state,u.rolecode=:rolecode,"
			+ "u.remark=:remark where u.code=:code")
	public void UptateUser(@Param("code") Integer code,@Param("name") String name,@Param("department") String department,
			@Param("state")String state,@Param("rolecode")Integer rolecode,@Param("remark")String remark);
	
	
}
