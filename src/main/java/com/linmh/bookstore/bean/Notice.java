package com.linmh.bookstore.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("title")
    private String title;
    @TableField("details")
    private String details;
    @TableField("time")
    private Date time;
}
