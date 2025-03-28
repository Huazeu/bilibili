package com.bilibili.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.mapper.CategoryMapper;
import com.bilibili.model.video.Category;
import com.bilibili.service.CategoryService;
import org.springframework.stereotype.Service;

/**
* @author 22147
* @description 针对表【category】的数据库操作Service实现
* @createDate 2025-03-27 19:19:54
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

}




