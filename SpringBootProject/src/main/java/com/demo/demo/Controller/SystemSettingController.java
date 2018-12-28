package com.demo.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.demo.demo.DemoApplication;
import com.demo.demo.Entity.Role;
import com.demo.demo.Entity.User;
import com.demo.demo.Service.SystemSettingService;
import com.demo.demo.Entity.Module;
@Controller
public class SystemSettingController {
	@Autowired
	private SystemSettingService systemSettingService;
	
	/**
	 * 打开角色增加页面
	 */
	@RequestMapping(value="/role/save",method=RequestMethod.GET)
	public String openRoleSave() {
		
		
		return "/system/roleadd";
	}
	/**
	 * 打开角色list界面
	 */
	@RequestMapping(value="/role/list",method=RequestMethod.GET)
	public String openRoleList(Model model,
			@PageableDefault(size=DemoApplication.PAGE_SIZE) Pageable pageable) {
		model.addAttribute("datas", systemSettingService.findAllRole(pageable));
		return "/system/rolelist";
	}
	/**
	 * 角色增加
	 * 
	 */
	
	@RequestMapping(value="/roleadd",method=RequestMethod.POST)
	public String roleSave(@ModelAttribute Role role) {
		
		systemSettingService.roleSave(role);
		return "redirect:/role/list";
	}
	/**
	 * 打开角色修改界面
	 */
	@RequestMapping(value="/roleupdate/{roleid}",method=RequestMethod.GET)
	public String openRoleUpdate(@PathVariable Integer roleid, @ModelAttribute Role role,
            Model model) {
		model.addAttribute("role",systemSettingService.findRoleById(roleid));
		model.addAttribute("readonly", true);
		return "/system/roleupdate";
	}
	
	/**
	 * 角色查询
	 * 
	 */
	@RequestMapping(value="/role/search",method=RequestMethod.GET)
	public String roleFind() {
		return "";
	}
	/**
	 * 角色删除
	 * 
	 */
	@RequestMapping(value="/role/delete/{page}",method=RequestMethod.POST)
	public String roleDelete(@RequestParam Integer [] ids,@PathVariable Integer page) {
		systemSettingService.roleDelete(ids);
		return "redirect:/role/list?page=" + page;
	}
	/**
	 * 打开菜单list页面
	 */
	@RequestMapping(value="/module/list",method=RequestMethod.GET)
	public String openModulelist(Model model,@PageableDefault(size=DemoApplication.PAGE_SIZE) Pageable pageable) {
		model.addAttribute("datas", systemSettingService.findALLModule(pageable));
		return "system/modulelist";
	}
	/**
	 * 打开菜单增加页面
	 */
	@RequestMapping(value="/module/save",method=RequestMethod.GET)
	public String openModuleadd() {
		return "system/moduleadd";
	}
	/**
	 * 打开菜单修改页面
	 */
	@RequestMapping(value="/moduleupdate/{moduleid}",method=RequestMethod.GET)
	public String openModuleupdate(@PathVariable Integer moduleid,@ModelAttribute Module module,Model model ) {
		model.addAttribute("module", systemSettingService.findModuleById(moduleid));
		model.addAttribute("readonly", true);
		return "system/moduleupdate";
	}
	/**
	 * 菜单增加
	 * 
	 */
	@RequestMapping(value="/modulesave",method=RequestMethod.POST)
	public String moduleSave(@ModelAttribute Module module) {
		systemSettingService.moduleSave(module);
		return "redirect:/module/list";
	}
	
	/**
	 * 菜单删除
	 
	 */
	@RequestMapping(value="/module/delete/{page}",method=RequestMethod.POST)
	public String moduleDelete(Integer [] ids,@PathVariable Integer page) {
		systemSettingService.delete(ids);
		return "redirect:/module/list?page=" + page;
	}
	/**
	 * 菜单查询
	 * 
	 */
	@RequestMapping(value="/module/search",method=RequestMethod.GET)
	public String moduleFind() {
		return "";
	}
	/**
	 * 打开权限分配页面
	 */
	@RequestMapping(value="/role/allot" ,method=RequestMethod.GET)
	public String openModuleDistribute() {
		return "/system/allotmodule";
	}
	/**
	 * 权限分配
	 * 
	 */
	@RequestMapping(value="/roleallot" ,method=RequestMethod.GET)
	public String moduleDistribute() {
		return "权限分配成功";
	}
}
