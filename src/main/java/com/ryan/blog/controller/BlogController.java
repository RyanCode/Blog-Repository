package com.ryan.blog.controller;


import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Blog;
import com.ryan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2020-07-20
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    /**
     * 获取所有文章属性
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/articles")
    public ResponseDTO getBlogs(){
        return blogService.getBlogs();
    }

    /**
     * 根据id获取文章属性
     * @param blog
     * @return
     */

    @RequestMapping(method = RequestMethod.POST,value = "/article")
    @ResponseBody
    public ResponseDTO getBlog(@RequestBody Blog blog){
        return blogService.getBlogById(blog.getId());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/articleDetail")
    public ResponseDTO getBlogs(@RequestParam int pageNumber, int pageSize, String keyWord){
        return blogService.getBlogDetail(pageNumber, pageSize, keyWord);
    }

    @RequestMapping(method = RequestMethod.POST,value ="/admin/post")
    @ResponseBody
    public ResponseDTO postArticle(@RequestBody Blog blog){
        return blogService.postBlog(blog);
    }
}
