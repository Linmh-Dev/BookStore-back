package com.linmh.bookstore.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    Order order;
    List<OrderItem> orderItems;
    String username;
}
