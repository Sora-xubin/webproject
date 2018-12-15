package com.demo.demo.Dao;

import com.demo.demo.Entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberDao extends JpaRepository<ProjectMember,Integer> {
}
