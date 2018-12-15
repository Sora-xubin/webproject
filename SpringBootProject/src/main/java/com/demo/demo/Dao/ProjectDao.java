package com.demo.demo.Dao;

import com.demo.demo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDao extends JpaRepository<Project,Integer>{
}
