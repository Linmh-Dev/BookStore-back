package com.linmh.bookstore.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("orderitem")
public class OrderItem {
    public OrderItem(String order_id, String product_id, Integer buyNum) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.buyNum = buyNum;
    }

    @TableField("order_id")
    private String order_id;
    @TableField("product_id")
    private String product_id;
    @TableField("buynum")
    private Integer buyNum;

    private transient Book book;
}
