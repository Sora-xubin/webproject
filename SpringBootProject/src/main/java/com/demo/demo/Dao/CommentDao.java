package com.demo.demo.Dao;

import com.demo.demo.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XB on 2018/12/19.
 */
public interface CommentDao extends JpaRepository<Comment,Integer> {
}
