package com.ryan.blog.controller;


import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Tome;
import com.ryan.blog.service.TomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2020-07-27
 */
@RestController
public class TomeController {
    @Autowired
    private TomeService tomeService;
    @RequestMapping(method = RequestMethod.POST,value = "/emailToMe")
    public ResponseDTO toMe(@RequestBody Tome tome){
        int insert = tomeService.lambdaUpdate().getBaseMapper().insert(tome);
        if (insert>0){
            return ResponseDTO.getInstance().suc(insert);
        }
        return ResponseDTO.getInstance().err("请求失败");
    }
}
