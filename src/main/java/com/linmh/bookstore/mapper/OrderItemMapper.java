package com.linmh.bookstore.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linmh.bookstore.bean.OrderItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    @Select("select * from orderitem where order_id=#{orderId}")
    List<OrderItem> selectByOrderId(String orderId);

    @Delete("delete from orderitem where order_id=#{orderId}")
    int deleteByOrderId(String orderId);
}
