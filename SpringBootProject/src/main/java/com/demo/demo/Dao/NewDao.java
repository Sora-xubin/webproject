package com.demo.demo.Dao;

import com.demo.demo.Entity.New;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewDao extends JpaRepository<New ,Integer> {
    public List<New> findAllByUsercode(Integer usercode);
}
