package com.demo.demo.Dao;

import com.demo.demo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleDao extends JpaRepository<Role,Integer> {
	
	@Query("select o.id from Role o where o.code=?1")
	public Integer findIdbyCode(Integer code);
	
}
