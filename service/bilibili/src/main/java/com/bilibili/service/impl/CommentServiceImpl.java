package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.CommentMapper;
import com.bilibili.model.interaction.Comment;
import com.bilibili.service.CommentService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2025-04-15 12:36:24
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

}




