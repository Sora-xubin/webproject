package com.demo.demo.Dao;

import com.demo.demo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectDao extends JpaRepository<Project,Integer>, JpaSpecificationExecutor {
    public Project findByCode(int code);
}
