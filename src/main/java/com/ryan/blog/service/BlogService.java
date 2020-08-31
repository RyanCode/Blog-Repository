package com.ryan.blog.service;

import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ryan
 * @since 2020-07-20
 */
public interface BlogService extends IService<Blog> {
    ResponseDTO getBlogById(int id);

    ResponseDTO getBlogDetail(int pageNumber,int pageSize,String keyWord);

    ResponseDTO getBlogs();

    ResponseDTO postBlog(Blog blog);
}
