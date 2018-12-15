package com.demo.demo.Controller;

import com.demo.demo.Dao.UserDao;
import com.demo.demo.Entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    UserDao userRepository;
    @RequestMapping("/hello")
    public String hi(){
        return "hello world";
    }

    @RequestMapping("/User")
    public List<User> users(){
        return userRepository.findAll();
    }
}
