package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.Book;
import com.linmh.bookstore.bean.Order;
import com.linmh.bookstore.bean.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderService {
    int addOrder(Order order, Map<String, Book> cart);
    int deliverOrder(String orderId);

    int activeOrder(Order orderId);

    int removeOrder(String orderId);

    Order queryOrder(String orderId);

    List<OrderInfo> selectOrder(int payState);

    List<OrderInfo> queryAllOrder();
}
