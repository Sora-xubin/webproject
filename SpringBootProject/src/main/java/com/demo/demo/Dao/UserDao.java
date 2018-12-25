package com.demo.demo.Dao;

import com.demo.demo.Entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserDao extends JpaRepository<User,Integer> {
   
	public String findPasswordByCode(Integer code);
    
	public Boolean findAllByCode(Integer code);
	
	
	@Query("select o.id from User o where o.code=?1 ")
	public Integer findIdByCode(Integer code);

	public List<User> findAllByRolecode(Integer rolecode);
	

	public User findByCode(int code);
	
	public List<User> findAllByRolecode(int rolecode);
	
	
	

}
