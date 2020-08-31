package com.ryan.blog.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Comment;
import com.ryan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2020-08-13
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST,value = "/comment/post")
    @ResponseBody
    public ResponseDTO postComment(@RequestBody Comment comment){
        return commentService.postComment(comment);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/comment/listComment")
    @ResponseBody
    public ResponseDTO getCommentById(@RequestParam int blogId){
        return commentService.getComment(blogId);
    }
}
