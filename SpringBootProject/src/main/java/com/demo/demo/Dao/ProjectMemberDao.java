package com.demo.demo.Dao;

import com.demo.demo.Entity.Project;
import com.demo.demo.Entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberDao extends JpaRepository<ProjectMember,Integer> {
    List<ProjectMember> findByusercode(Integer userCode);
}
