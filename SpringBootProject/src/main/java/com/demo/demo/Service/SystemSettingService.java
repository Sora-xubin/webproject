package com.demo.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.demo.Dao.ModuleDao;
import com.demo.demo.Dao.RoleDao;
import com.demo.demo.Dao.RoleModuleDao;
import com.demo.demo.Entity.Role;
import com.demo.demo.Entity.RoleModule;
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
	 * 返回所有的分页数据
	 */
	public Page<Module> findALLModule(Pageable pageable){
		return moduleDao.findAll(pageable);
	}
	/**
	 * 
	 * 查询所有模块的数据
	 * 返回list
	 */
	public List<Module> findAllModule(){
		return moduleDao.findAll();
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
	
	/**
	 * 根据角色 code找到角色对应的所有模块返回一个rolemodule list
	 */
	public List<RoleModule> findAllRoleModuleByCode(Integer code) {
		
		Example<RoleModule> example = getRoleModuleByRoleCode(code);
		return roleModuleDao.findAll(example);
	}
	/**
	 * 根据角色code建立对应的example
	 */
	private Example<RoleModule> getRoleModuleByRoleCode( Integer code){
		RoleModule roleModule = new RoleModule ();
		roleModule.setRoleid(code);
		return Example.of(roleModule);
	}
	
	/**
	 * 根据给定的模块的code找到对应的模块list
	 */
	public List<Module> findAllModuleByRoleModule(List <RoleModule> roleModules){
		 List<Module> modules = new ArrayList<Module> ();
		for(RoleModule roleModule: roleModules) {
			modules.add(moduleDao.findByCode((roleModule.getModuleid())));
		 }
		return modules;
	}
	/**
	 * 把得到的module的list 通过遍历把相同类型的1，2级菜单对象组成list放在一个map的其中一个,key值为1级菜单的名字
	 * @param modules
	 * @return
	 */
	public Map<String,List<Module>> getModuleByLevel(List<Module> modules){
		Map<String,List<Module>> modulemap = new HashMap<String,List<Module>> ();
		for(Module module:modules) {
			if(module.getLevel()==0) {
	
				List<Module> samemodule = new ArrayList<Module>();
				samemodule.add(module);
				for(Module module1:modules) {
					if(module1.getLevel()==module.getId()) {
					samemodule.add(module1)	;
					}
				modulemap.put(module.getModulename(), samemodule);
				}
			}
		}
		return modulemap;
	}
}
