package com.ryan.blog.service.impl;

import com.ryan.blog.entity.Tome;
import com.ryan.blog.mapper.TomeMapper;
import com.ryan.blog.service.TomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2020-07-27
 */
@Service
public class TomeServiceImpl extends ServiceImpl<TomeMapper, Tome> implements TomeService {

}
