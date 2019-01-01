package com.demo.demo.Controller;

import com.demo.demo.DemoApplication;
import com.demo.demo.Dao.NewDao;
import com.demo.demo.Entity.Comment;
import com.demo.demo.Entity.New;
import com.demo.demo.Entity.Project;
import com.demo.demo.Entity.User;
import com.demo.demo.Service.DeclareService;
import com.demo.demo.Service.NewService;
import com.demo.demo.Service.SystemSettingService;
import com.demo.demo.Service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    NewService newService = new NewService();
    @Autowired
    NewDao newDao;
    @Autowired
    DeclareService declareService;

    @Autowired
    private UserService userService ;
    
    @Autowired
    private SystemSettingService systemSettingService;
    
    @RequestMapping("/hello")
    public String hi(){
        return "hello world";
    }

   
    
	/**
	 * 打开新增的页面
	 * 
	 */

	@RequestMapping(value = "/user/save", method = RequestMethod.GET)
	public String userAdd() {
		return "/user/add";
	}
	/**
	 * 
	 * 实现新增用户并保存
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/useradd", method = RequestMethod.POST,produces= {"application/json;charset=utf-8"})	
	public String userSave(@ModelAttribute User user) {//HttpServletRequest req ,HttpServletResponse res) throws UnsupportedEncodingException {	
		/* req.setCharacterEncoding("UTF-8");
		Integer  code=Integer.valueOf (req.getParameter("code"));
	    String  name= req.getParameter("name");
	    String  password = req.getParameter("password");
		String department = req.getParameter("department");
		String remark = req.getParameter("remark");
		String createtime = req.getParameter("createtime");
		User user = new User( code, password,  name,  department, createtime, remark);*/
		userService.save(user);		
	
		return "redirect:/user/list";
	}
	/**
	 * 打开修改用户界面
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/userupdate/{userid}", method = RequestMethod.GET)
	
	public String openUpdate(@PathVariable Integer userid, @ModelAttribute User user,
            Model model,Pageable pageable) {
		
		model.addAttribute("user",userService.findById(userid));
		model.addAttribute("readonly", true);
		model.addAttribute("datas",systemSettingService.findAllRole(pageable));
		return "/user/update";
	}
	/**
	 * 打开list界面
	 */
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
	
	public String openList(Model model,
            @PageableDefault(size = DemoApplication.PAGE_SIZE) Pageable pageable) {
        model.addAttribute("datas", userService.findAllUser(pageable));
        
		return "/user/list";
	}
	
	
	

	/**
	 * 删除用户
	 * 
	 */
	@RequestMapping(value = "/user/delete/{page}", method = RequestMethod.POST)
	
	public String delete(@RequestParam Integer[] ids, @PathVariable Integer page) {
		
		userService.delete(ids);
		
		return "redirect:/user/list?page=" + page;
	}

	/**
	 * 查询所有用户，返回用户信息
	 * 
	 */
	@RequestMapping(value = "/usersearch", method = RequestMethod.GET)
	@ResponseBody
	public String search() {
		return "查询结果";
	}
	/**
	 * 打开密码重置页面
	 * 
	 */
	@RequestMapping(value = "/user/password", method = RequestMethod.GET)
	public String openResetPwd() {
		return "/user/resetpwd";
	}
	
	/**
	 * 
	 * 实现密码重置
	 * 
	 */
	
	@RequestMapping(value = "/user/reset", method = RequestMethod.POST)
	public String resetPassword(String password) {

		return "user/list";
	}
	/**
	 * 打开角色分配页面
	 * 
	 */
	@RequestMapping(value = "/user/roleallot", method = RequestMethod.GET)
	public String openRoleallot( Model model,Pageable pageable) {
		model.addAttribute("datas",systemSettingService.findAllRole(pageable));
		
		return "user/allot";
	}
	/**
	 * 角色分配
	 * 
	 */
	@RequestMapping(value = "/user/distribute/{userid}",method = RequestMethod.GET)

	public void distributeUser(@PathVariable Integer userid,HttpServletRequest req ,HttpServletResponse res) {
		Integer rolecode = Integer.valueOf(req.getParameter("rolecode")) ;
//		System.out.println(userid);
//		System.out.println(code);
		User user  = userService.findById(userid);
		user.setRolecode(rolecode);
		userService.save(user);
	}


}
