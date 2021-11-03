package com.linmh.bookstore.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("orders")
public class Order {
    private String id;
    private Double money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    @TableField("paystate")
    private Integer payState;//0未付款 1完成付款 2订单完成
    @TableField("ordertime")
    private Date orderTime;
    @TableField("user_id")
    private Integer userId;

}
