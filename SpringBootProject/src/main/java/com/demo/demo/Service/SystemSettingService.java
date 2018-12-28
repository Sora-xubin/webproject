package com.demo.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.demo.Dao.ModuleDao;
import com.demo.demo.Dao.RoleDao;
import com.demo.demo.Dao.RoleModuleDao;
import com.demo.demo.Entity.Role;
import com.demo.demo.Entity.Module;
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
	public void roleDelete(Integer [] ids) {
		
		for(Integer id:ids) {
			roleDao.deleteById(id);
		}
		
	}
	
	
	/**
	 * 角色修改
	 */
	public void roleUpdate(Role role) {
		
	}
	/**
	 * 查询所有角色的数据
	 */
	public Page<Role> findAllRole(Pageable pageable) {
		return roleDao.findAll(pageable);
	}
	/**
	 *根据ID找到role对象
	 */
	public Role findRoleById(Integer id) {
		return roleDao.findById(id).get();
	}
	/**
	 * 查询所有模块的数据
	 */
	public Page<Module> findALLModule(Pageable pageable){
		return moduleDao.findAll(pageable);
	}
	/**
	 * 根据id找到module对象
	 */
	public Module findModuleById(Integer id) {
		return moduleDao.findById(id).get();
		
	}
	/**
	 * 删除选中模块
	 */
	public void delete(Integer [] ids) {
		for(Integer id : ids) {
			moduleDao.deleteById(id);;
		}
	}
	/**
	 * 添加一个菜单
	 */
	public void moduleSave(Module module) {
		moduleDao.save(module);
		
	}
	
}
