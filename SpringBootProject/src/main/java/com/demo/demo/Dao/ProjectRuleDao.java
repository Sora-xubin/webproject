package com.demo.demo.Dao;

import com.demo.demo.Entity.ProjectRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRuleDao extends JpaRepository<ProjectRule,Integer> {
    public ProjectRule findFirstByOrderById();
}
