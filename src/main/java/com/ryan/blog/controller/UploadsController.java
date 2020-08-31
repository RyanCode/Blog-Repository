package com.ryan.blog.controller;


import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.service.UploadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2020-08-11
 */
@RestController
public class UploadsController {
    @Autowired
    private UploadsService uploadsService;
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST,value = "/admin/upload")
    public ResponseDTO doUploadImg(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request,String user) throws FileNotFoundException {
        return uploadsService.uploadImg(multipartFile,request,user);
    }

}
