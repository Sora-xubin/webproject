package com.demo.demo.Service;

import com.demo.demo.Dao.UserDao;
import com.demo.demo.Entity.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    
    public void save(User user) {
    	
    	userDao.save(user);
    	
    
    }
    
    
    public User findById(Integer id){
    	return userDao.findById(id).get();
    }    /**
     * 根据用户code删除用户
     * @param code
     */

    public void delete(Integer[] ids) {
    	for(Integer id:ids) {
    	userDao.deleteById(id);
    	}
    }
    /**
     * 根据新用户信息修改信息
     * @param user
     */

    public void update(User user) {
    	
    	
    	
    	
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
