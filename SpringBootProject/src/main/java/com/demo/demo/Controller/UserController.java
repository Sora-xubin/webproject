package com.demo.demo.Controller;

import com.demo.demo.Dao.NewDao;
import com.demo.demo.Entity.Comment;
import com.demo.demo.Entity.New;
import com.demo.demo.Entity.Project;
import com.demo.demo.Service.DeclareService;
import com.demo.demo.Service.NewService;
import com.demo.demo.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.PostConstruct;

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
	 */
	@RequestMapping(value="/useradd", method = RequestMethod.GET)
	@ResponseBody
	public String userSave() {
		
		
		return "新增成功";
	}
	/**
	 * 打开修改用户界面
	 * 和新增是同一个页面
	 * 
	 * 
	 */
	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	
	public String openUpdate() {
		
		return "/user/add";
	}
	/**
	 * 进行数据修改
	 */
	@RequestMapping(value="/userupdate", method = RequestMethod.GET)
	@ResponseBody
	public String updateUser() {
		return "修改成功";
	}
	/**
	 * 删除一个用户
	 * 
	 */
	@RequestMapping(value = "/userdelete", method = RequestMethod.POST)
	@ResponseBody
	public String delete() {
		return "删除成功";
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
		return "/user/password";
	}
	
	/**
	 * 
	 * 实现密码重置
	 * 
	 */
	
	@RequestMapping(value = "/user/password", method = RequestMethod.POST)
	public String resetPassword(String password) {

		return "user/main";
	}
	
	@RequestMapping(value = "/user/roleallot", method = RequestMethod.GET)
	public String openRoleallot() {
		return "user/allot";
	}
	/**
	 * 角色分配
	 * 
	 */
	@RequestMapping(value = "/user/distribute", method = RequestMethod.GET)

	public String distributeUser() {
		return "角色分配成功";
	}


}
