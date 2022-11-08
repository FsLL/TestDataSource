package com.lqj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqj.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lqj
 * @Date 2022-10-24 15:18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}


