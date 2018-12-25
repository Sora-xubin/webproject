package com.demo.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demo.Dao.ModuleDao;
import com.demo.demo.Dao.RoleDao;
import com.demo.demo.Dao.RoleModuleDao;
import com.demo.demo.Entity.Role;
import org.springframework.stereotype.Service;

@Service
public class SystemSettingService {
	
	@Autowired
	private ModuleDao moduleDao;
	
	@Autowired
	private RoleDao roleDao;
		
	@Autowired 
	private RoleModuleDao roleModuleDao;
	
	
	/**
	 * 角色增加
	 * 
	 */
	public void roleSave(Role role) {
		roleDao.save(role);
	}
	/**
	 * 通过角色code删除角色
	 * 
	 */
	public void roleDelete(Integer code) {
		roleDao.deleteById(roleDao.findIdbyCode(code));;
	}
	
	/**
	 * 角色修改
	 */
	public void roleUpdate(Role role) {
		
	}
}
