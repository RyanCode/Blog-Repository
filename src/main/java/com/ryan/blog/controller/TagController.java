package com.ryan.blog.controller;


import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Tag;
import com.ryan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2020-07-24
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET,value = "/category")
    @ResponseBody
    public ResponseDTO getCategory(){
        return tagService.getTages();
    }
}
