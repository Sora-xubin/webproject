package com.demo.demo.Dao;

import com.demo.demo.Entity.Comment;
import com.demo.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by XB on 2018/12/19.
 */
public interface CommentDao extends JpaRepository<Comment,Integer> {
    public Comment findByProjectcodeAndExpertcode(int projectcode,int expertcode);
    public List<Comment> findAllByExpertcode(int expertcode);
    public List<Comment> findAllByProjectcode(int projectcode);
    Page<Comment> findAll(Specification<Comment> state, Pageable pageable);
}
