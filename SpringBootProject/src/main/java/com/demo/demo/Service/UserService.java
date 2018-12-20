package com.demo.demo.Service;

import com.demo.demo.Dao.UserDao;
import com.demo.demo.Entity.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     * 新增用户
     */
    @Transactional
    public String save(User user) {
    	if(userDao.findAllByCode(user.getCode())) {
    	userDao.save(user);
    	
    	return "新增成功";
    	}
    	else 
    	return "账户已存在，新增失败";
    }
    
    /**
     * 根据用户code删除用户
     * @param code
     */
    @Transactional
    public void delete(Integer code) {
    	
    	userDao.deleteById(userDao.findIdByCode(code));
    	
    }
    @Transactional
    public void update(User user) {
    	
    	userDao.UptateUser(user.getCode(), user.getName(),
    			user.getDepartment(), user.getState(), 
    			user.getRolecode(), user.getRemark());
    	
    	
    	
    }
    /**
     * 查找所有的用户
     * 
     * 
     */
    public Page<User> findAllUser(Pageable pageable) {
    	
    	return userDao.findAll(pageable);
    }
}
