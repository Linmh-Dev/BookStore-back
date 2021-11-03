package com.linmh.bookstore.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("products")
public class Book {
    private String id;
    @TableField("name")
    private String name;

    private double price;

    private String category;

    private transient Integer num;

    private Integer pnum;

    private String imgurl;

    private String description;

}
