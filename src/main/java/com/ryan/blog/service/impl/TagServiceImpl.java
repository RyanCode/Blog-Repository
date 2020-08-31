package com.ryan.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Tag;
import com.ryan.blog.mapper.TagMapper;
import com.ryan.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2020-07-24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    @Cacheable(cacheNames = "tag",key = "#root.methodName")
    public ResponseDTO getTages() {
        QueryWrapper<Tag> queryWrapper=new QueryWrapper<>();
        List<Tag> list=tagMapper.selectList(queryWrapper);
        return ResponseDTO.getInstance().suc(list);
    }
}
