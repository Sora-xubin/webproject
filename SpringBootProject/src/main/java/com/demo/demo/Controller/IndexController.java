package com.demo.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.demo.Entity.User;
import com.demo.demo.Service.SystemSettingService;
import com.demo.demo.Service.UserService;
import com.demo.demo.Entity.Module;;

@Controller


public class IndexController {
	@Autowired
	private UserService userService;
	@Autowired
	private SystemSettingService systemSettingService;
	
	@GetMapping(value= "/")
	public String login(@ModelAttribute User user) {
		return "login";
	}
	
	@GetMapping(value = "/index")
    public String main(Model model) {
        return "home";
    }
	
	@PostMapping(value="/login")
	public String login(@ModelAttribute User user,HttpSession session,Model model) {
		System.out.println(user.getCode() + "---" + user.getPassword());
		User loginuser = userService.login(user);
		if(loginuser == null) {
			//登录失败
			model.addAttribute("msg","用户名密码错误！");
			return "login";
		}
		else {
			//登录成功
			session.setAttribute("user", loginuser);
			List<Module> lists = systemSettingService.findAllModuleByRoleModule(systemSettingService.findAllRoleModuleByCode(loginuser.getRolecode()));
			Map<String,List<Module>> modulemap = systemSettingService.getModuleByLevel(lists);
			model.addAttribute("datas", modulemap);
			model.addAttribute("user",loginuser);
			//model.addAttribute("datas", lists);
			return "home";
		}
	}
}
