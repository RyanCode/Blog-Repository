package com.ryan.blog.service;

import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Uploads;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ryan
 * @since 2020-08-11
 */
public interface UploadsService extends IService<Uploads> {
    ResponseDTO uploadImg(MultipartFile File, HttpServletRequest request,String user) throws FileNotFoundException;

}
