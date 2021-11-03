package com.linmh.bookstore.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linmh.bookstore.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name=#{name}")
    User selectUserByName(String name);

    @Update("update user set avatar=#{avatarUrl} where name=#{username}")
    int updateAvatar(String username,String avatarUrl);
}
