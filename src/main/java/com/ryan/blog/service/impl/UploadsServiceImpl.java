package com.ryan.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Uploads;
import com.ryan.blog.mapper.UploadsMapper;
import com.ryan.blog.service.UploadsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2020-08-11
 */

@Service
public class UploadsServiceImpl extends ServiceImpl<UploadsMapper, Uploads> implements UploadsService {

    @Autowired
    private UploadsMapper uploadsMapper;
    @Override
    public ResponseDTO uploadImg(MultipartFile file, HttpServletRequest request,String user) {
        Uploads uploadImg=new Uploads();
        //获取jar包同级路径
        try {
            File path=new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()){
                path=new File("");
            }
            File upload=new File(path.getAbsolutePath(),"/static/upload");
            if (!upload.exists()){
                upload.mkdirs();
            }
            //初始化实体类
            uploadImg.setName(System.currentTimeMillis()+ "." + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")+1))
                    .setDate(new Date())
                    .setHost(user)
                    .setPath("http://www.ryanmoon.cool/banner/"+uploadImg.getName())
                    .setSize(file.getSize())
                    .setType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
            //nginx路径资源
            File nginxDest=new File(path.getAbsolutePath().replaceAll("/jars","")+"/nginx/WWW/banner/"+uploadImg.getName());
            //上传文件
            file.transferTo(nginxDest);

            //存入数据库
            int insert = uploadsMapper.insert(uploadImg);
            if (insert>0){
                return ResponseDTO.getInstance().suc(uploadImg);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
