package com.demo.demo.Controller;


import com.demo.demo.Dao.NewDao;
import com.demo.demo.Entity.New;
import com.demo.demo.Service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    NewService newService = new NewService();
    @Autowired
    NewDao newDao;
    @RequestMapping("/hello")
    public String hi(){
        return "hello world";
    }

    @RequestMapping("/News")
    public List<New> news(){
        New n = new New();
        n.setNews("llala");
        n.setState("1");
        n.setUsercode(123);
        n.setTime("1221");
        newService.saveNew(n);

        return newDao.findAll();
    }
}
