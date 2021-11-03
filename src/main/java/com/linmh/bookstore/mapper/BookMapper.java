package com.linmh.bookstore.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linmh.bookstore.bean.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("select * from products where category=#{category}")
    List<Book> selectByType(String category);

    @Select("select * from products WHERE name like '%${key}%'")
    List<Book> selectByKey(String key);

    @Select("select * from products WHERE name =#{bookName}")
    List<Book> selectByName(String bookName);

    @Select("select * from products,(select product_id,sum(buynum) as num from orderitem GROUP BY product_id order by num desc limit ${num}) as b where products.id=b.product_id order by num desc")
    List<Book> selectHotSale(Integer num);

    @Select("select products.id,products.name,sum(buynum) as num from products JOIN\n" +
            "(select * from orderitem where order_id in \n" +
            "(select id from orders where ordertime >=#{start} and ordertime<#{end})) as b\n" +
            "on b.product_id=products.id GROUP BY products.id order by num desc")
    List<Book> selectBookList(String start,String end);
}
