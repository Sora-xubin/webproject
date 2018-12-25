package com.demo.demo.Controller;


import com.demo.demo.Dao.NewDao;
import com.demo.demo.Entity.New;
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
    private UserService userService ;

    @RequestMapping("/News")
    @ResponseBody
    public List<New> news(){
        New n = new New();
        n.setNews("llala");
        n.setState("1");
        n.setUsercode(123);
        n.setTime("1221");
        newService.saveNew(n);

        return newDao.findAll();
    }
    
    
    /**
     * 保存一个用户并跳转到list界面
     * xtf
     */
    @RequestMapping(value="/user/save" ,method=RequestMethod.POST)
    public String save() {
    	
    	return "/user/list";
    }
    /**
     * 修改一个用户信息并跳转到list界面
     * 
     * @xtf
     */
    @RequestMapping(value="/user/update",method=RequestMethod.POST)
    public String update() {
    	return "/user/main";
    }
    
    /**
     * 删除一个用户并跳转到list界面
     * @xtf
     */
    @RequestMapping(value="/user/delete",method=RequestMethod.POST)
    public String delete() {
    	return "/user/main";
    }
    /**
     * 查询所有用户，返回用户信息
     * @return
     */
    @RequestMapping(value="/user/search" ,method=RequestMethod.GET)
    @ResponseBody
    public String findAll() {
    	return "";
    }
    
    /**
     * 
     * 密码重置
     * @xtf
     */
    @RequestMapping(value="/user/password",method=RequestMethod.POST)
    public String resetPassword(String password) {
    	
    	return "user/main";    }
    
    /**
     * 角色分配
     * @xtf
     */
    @RequestMapping(value="/user/distribute",method=RequestMethod.GET)
    
    public String distributeUser() {
    	return "user/main";
    }
    
}
