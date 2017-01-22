package com.wyl.comment.service;

import com.wyl.comment.module.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wangyulin on 21/01/2017.
 */
public interface CommentService {

    public Comment save(Comment comment);

    public long update(Comment comment);

    public List<Comment> findAllComment();

    public Page<Comment> findCommentPage(Pageable pageable);

}
