package com.ryan.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Blog;
import com.ryan.blog.mapper.BlogMapper;
import com.ryan.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2020-07-20
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public ResponseDTO getBlogById(int id) {
        QueryWrapper<Blog> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Blog::getId,id);
        List<Blog> blog= blogMapper.selectList(queryWrapper);
        return ResponseDTO.getInstance().suc(blog);
    }

    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "articles",key = "#root.args")
            }
    )

    @Override
    public ResponseDTO getBlogDetail(int pageNumber,int pageSize, String keyWord) {
        QueryWrapper<Blog> queryWrapper=new QueryWrapper<>();
        if (!keyWord.equals("Home")){
            queryWrapper.and(
                    wrapper ->
                            wrapper.like("title", keyWord).or().like("description", keyWord)
            );
        }
        IPage<Blog> blogIPage=new Page<>(pageNumber,pageSize,true);
        blogIPage=blogMapper.selectPage(blogIPage,queryWrapper);
        long total = blogIPage.getTotal();
        List<Object> list=new LinkedList<>();
        list.add(total);
        list.add(blogIPage.getRecords());
        return ResponseDTO.getInstance().suc(list);
    }

    @Override
    public ResponseDTO getBlogs() {
        QueryWrapper<Blog> queryWrapper=new QueryWrapper<>();
        List<Blog> blogs=blogMapper.selectList(queryWrapper);
        return ResponseDTO.getInstance().suc(blogs);
    }

    @Override
    @CachePut(cacheNames ="articles")
    public ResponseDTO postBlog(Blog blog) {
        redisUtil.flush();
        int insert = blogMapper.insert(blog);
        if (insert>0){
            return ResponseDTO.getInstance().suc("发表成功！");
        }
        return ResponseDTO.getInstance().err("插入失败");
    }
}
