package com.linmh.bookstore.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linmh.bookstore.bean.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    @Select("select * from address where username=#{username}")
    Address selectAddressByUsername(String username);

    @Delete("delete from address where username=#{username}")
    int deleteByUsername(String username);

}
