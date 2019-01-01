package com.demo.demo.Dao;

import com.demo.demo.Entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project,Integer>{
    Project findByCode(int code);
    Page<Project> findAll(Specification<Project> state, Pageable pageable);

    List<Project> findAllByState(int state);
}
