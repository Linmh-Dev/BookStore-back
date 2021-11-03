package com.linmh.bookstore.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linmh.bookstore.bean.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    @Select("select * from notice order by time desc limit 1")
    Notice getNotice();
}
