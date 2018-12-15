package com.demo.demo.Dao;

import com.demo.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer> {

}
