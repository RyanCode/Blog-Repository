package com.ryan.blog.service;

import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ryan
 * @since 2020-07-24
 */
public interface TagService extends IService<Tag> {
    ResponseDTO getTages();
}
