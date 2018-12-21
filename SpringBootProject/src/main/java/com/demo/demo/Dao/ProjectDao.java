package com.demo.demo.Dao;

import com.demo.demo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project,Integer>{
    public Project findByCode(int code);
    public List<Project> findAllByState(int state);
}
