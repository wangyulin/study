package com.wyl.comment.service.impl;

import com.wyl.comment.dao.CommentRepository;
import com.wyl.comment.module.Comment;
import com.wyl.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wangyulin on 21/01/2017.
 */

@Component("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public long update(Comment comment) {
        return this.commentRepository.update(comment.getId(), comment.getContent(), comment.getUpdateTime());
    }

    @Override
    public List<Comment> findAllComment() {
        return this.commentRepository.findAll();
    }

    @Override
    public Page<Comment> findCommentPage(Pageable pageable) {
        return this.commentRepository.findAll(pageable);
    }
}
