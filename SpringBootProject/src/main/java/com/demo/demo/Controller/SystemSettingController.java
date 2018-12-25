package com.demo.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;

import com.demo.demo.Service.SystemSettingService;

@Controller
public class SystemSettingController {
	@Autowired
	private SystemSettingService systemSettingService;
	/**
	 * 角色增加
	 * 
	 */
	
	@RequestMapping(value="/system/role/save",method=RequestMethod.POST)
	public String roleSave() {
		return "/system/role/main";
	}
	
	/**
	 * 角色修改
	 * 
	 */
	
	@RequestMapping(value="/system/role/update",method=RequestMethod.POST)
	public String roleUpdate() {
		return "/system/role/main";
	}
	/**
	 * 角色查询
	 * 
	 */
	@RequestMapping(value="/system/role/search",method=RequestMethod.GET)
	public String roleFind() {
		return "";
	}
	/**
	 * 角色删除
	 * 
	 */
	@RequestMapping(value="/system/role/delete",method=RequestMethod.GET)
	public String roleDelete() {
		return "/system/role/main";
	}
	/**
	 * 菜单增加
	 * 
	 */
	@RequestMapping(value="/system/module/save",method=RequestMethod.GET)
	public String moduleSave() {
		return "/system/module/main";
	}
	/**
	 * 菜单删除
	 
	 */
	@RequestMapping(value="/system/module/delete",method=RequestMethod.GET)
	public String moduleDelete() {
		return "/system/module/main";
	}
	/**
	 * 菜单查询
	 * 
	 */
	@RequestMapping(value="/system/module/search",method=RequestMethod.GET)
	public String moduleFind() {
		return "";
	}
	/**
	 * 菜单修改
	 * 
	 * 
	 */
	@RequestMapping(value="/system/module/update",method=RequestMethod.POST)
	public String moduleUpdate() {
		return "/system/module/main";
	}
	/**
	 * 权限分配
	 * 
	 */
	@RequestMapping(value="/system/role/distribute" ,method=RequestMethod.GET)
	public String moduleDistribute() {
		return "/system/role/main";
	}
}
