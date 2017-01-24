package com.wyl.comment.dao;

import com.wyl.comment.module.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;


/**
 * Created by wangyulin on 20/01/2017.
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //Comment save(Comment comment);

    @Modifying
    @Query("update Comment c set c.content = ?2,c.updateTime = ?3 where c.id = ?1")
    int update(long id, String content, Date updateTime);

    List<Comment> findAll();

    Page<Comment> findAll(Pageable pageable);

}
