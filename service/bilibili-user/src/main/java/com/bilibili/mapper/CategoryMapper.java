package com.bilibili.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bilibili.model.video.Category;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 22147
* @description 针对表【category】的数据库操作Mapper
* @createDate 2025-03-27 19:19:54

*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




