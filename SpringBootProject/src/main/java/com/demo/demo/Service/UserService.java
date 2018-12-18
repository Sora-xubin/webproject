package com.demo.demo.Service;

import com.demo.demo.Dao.UserDao;
import com.demo.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XB on 2018/12/18.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public Boolean login(Integer code,String paswd){
        if (paswd == userDao.findPasswordByCode(code)){
            return true;
        }else return false;
    }

    public String register(User user){
        if (userDao.findAllByCode(user.getCode())){
            return "账户已存在";
        }else {
            userDao.save(user);
            return "注册成功";
        }
    }
}
