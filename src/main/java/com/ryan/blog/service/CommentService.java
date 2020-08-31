package com.ryan.blog.service;

import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ryan
 * @since 2020-08-13
 */
public interface CommentService extends IService<Comment> {

    ResponseDTO getComment(Integer blogId);

    ResponseDTO postComment(Comment comment);
}
