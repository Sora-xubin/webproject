package com.demo.demo.Dao;

import com.demo.demo.Entity.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewDao extends JpaRepository<New ,Integer> {
    Page<New> findAllByUsercode(Specification<New> state, Pageable pageable);
}
