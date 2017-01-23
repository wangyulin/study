package com.wyl.comment.controller;

import com.wyl.comment.module.Comment;
import com.wyl.comment.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyulin on 21/01/2017.
 */
@RestController
public class CommentController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public Comment addComment(@RequestBody Comment comment) {
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        Comment result = this.commentService.save(comment);
        return result;
    }

    @PutMapping("comment")
    public long update(@RequestBody Comment comment) {
        comment.setUpdateTime(new Date());
        long res = this.commentService.update(comment);
        return res;
    }

    @GetMapping("list")
    public List<Comment> findAllComment() {
        List<Comment> list = this.commentService.findAllComment();
        return list;
    }

    @GetMapping("page")
    public Page<Comment> findCommentPage() {
        Pageable pageable = new PageRequest(0, 4, Sort.Direction.ASC, "updateTime");
        logger.info("Tag : {}", pageable);
        Page<Comment> page = this.commentService.findCommentPage(pageable);
        return page;
    }

}
