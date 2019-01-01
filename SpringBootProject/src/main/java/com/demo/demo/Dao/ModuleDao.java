package com.demo.demo.Dao;

import com.demo.demo.Entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleDao extends JpaRepository<Module, Integer> {
	
	/**
	 * 通过module的code找到module
	 * 
	 */
	public Module findByCode(Integer code);
}
