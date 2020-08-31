package com.ryan.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryan.blog.dto.ResponseDTO;
import com.ryan.blog.entity.Comment;
import com.ryan.blog.mapper.CommentMapper;
import com.ryan.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2020-08-13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResponseDTO getComment(Integer blogId) {
        QueryWrapper<Comment> commentQueryWrapper=new QueryWrapper<>();
        commentQueryWrapper.lambda().eq(Comment::getBlogid,blogId);
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        List<Comment> reply;//放置二级回复json
        List<Object> list=new ArrayList<>();
        List<Comment> level1 = comments.stream()
                .filter(e -> e.getBelong()==0)
                .sorted((o1, o2) -> (int) (o2.getDate().getTime()-o1.getDate().getTime()))
                .collect(Collectors.toList());//放置过滤后的一级回复json,

        List<List<Object>> formatList=new ArrayList<>();
        for (int i=0;i<level1.size();i++){
            int finalI = i;
            reply=comments.stream()
                    .filter(e->e.getBelong()==level1.get(finalI).getId())
                    .sorted((o1, o2) -> (int) (o2.getDate().getTime()-o1.getDate().getTime()))
                    .collect(Collectors.toList());
            list.add(reply);
            list.add(level1.get(finalI));
            List<Object> list1=new ArrayList<>();
            for (int j=0;j<2;j++){
                list1.add(list.get(j));
            }
            formatList.add(list1);
            list.clear();
        }
        return ResponseDTO.getInstance().suc(formatList);
    }

    @Override
    public ResponseDTO postComment(Comment comment) {

        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Comment::getId,comment.getBelong());
        List<Comment> commentBelong = commentMapper.selectList(queryWrapper);
        if (commentBelong.size()>0){
            Comment comment1 = commentBelong.get(0);
            if (comment1.getBelong()!=0){
                comment.setBelong(comment1.getBelong());
            }
        }
        int insert = commentMapper.insert(comment);
        if (insert>0){
            return ResponseDTO.getInstance().suc(true);
        }
        return ResponseDTO.getInstance().err("错误！");
    }
}
