package com.demo.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.demo.Dao.ModuleDao;
import com.demo.demo.Dao.RoleDao;
import com.demo.demo.Dao.RoleModuleDao;
import com.demo.demo.Entity.Role;


public class SystemSettingService {
	
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private RoleDao roleDao;
	
	@Autowired 
	private RoleModuleDao roleModuleDao;
	
	
	/**
	 * 角色增加
	 * @xtf
	 */
	public void roleSave(Role role) {
		roleDao.save(role);
	}
	/**
	 * 角色id
	 * @param id
	 */
	public void roleDelete(Integer id) {
		roleDao.deleteById(id);
	}
	public void roleUpdate() {
		
	}
}
