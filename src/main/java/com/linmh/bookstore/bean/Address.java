package com.linmh.bookstore.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("address")
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String detail;
    private String desc;
}
