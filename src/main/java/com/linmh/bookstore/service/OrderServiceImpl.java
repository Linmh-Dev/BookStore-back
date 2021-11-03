package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.*;
import com.linmh.bookstore.mapper.BookMapper;
import com.linmh.bookstore.mapper.OrderItemMapper;
import com.linmh.bookstore.mapper.OrderMapper;
import com.linmh.bookstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public int addOrder(Order order, Map<String, Book> cart) {
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");
        order.setId(orderId);
        order.setOrderTime(new Date());
        order.setPayState(0);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.selectUserByName(username);
        order.setUserId(user.getId());

        for (Map.Entry<String, Book> entry : cart.entrySet()) {
            Book value = entry.getValue();
            OrderItem orderItem = new OrderItem(orderId,value.getId(),value.getNum());
            Integer orderItemRes = orderItemMapper.insert(orderItem);
            if (orderItemRes<0){
                throw new IllegalStateException();
            }
        }
        Integer res = orderMapper.insert(order);
        return res;
    }

    @Override
    public int deliverOrder(String orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order==null)return -1;
        order.setPayState(2);
        return orderMapper.updateById(order);
    }

    @Transactional
    @Override
    public int activeOrder(Order postOrder) {
        Order order = orderMapper.selectById(postOrder.getId());
        if (order == null) {
            return -1;
        }
        order.setPayState(1);
        order.setReceiverPhone(postOrder.getReceiverPhone());
        order.setReceiverName(postOrder.getReceiverName());
        order.setReceiverAddress(postOrder.getReceiverAddress());
        orderMapper.updateById(order);
        return 1;
    }

    @Transactional
    @Override
    public int removeOrder(String orderId) {
        int res = orderItemMapper.deleteByOrderId(orderId);
        if (res < 0) {
            throw new IllegalStateException();
        }
        return orderMapper.deleteById(orderId);
    }

    @Override
    public Order queryOrder(String orderId) {
        Order order = orderMapper.selectById(orderId);
        return order;
    }

    @Override
    public List<OrderInfo> queryAllOrder() {
        ArrayList<OrderInfo> orderInfos = new ArrayList<>();
        List<Order> list = orderMapper.selectList(null);
        for (Order order : list) {
            List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getId());
            for (OrderItem item : orderItems) {
                Book book = bookMapper.selectById(item.getProduct_id());
                item.setBook(book);
            }
            User user = userMapper.selectById(order.getUserId());
            if (user == null)continue;
            OrderInfo orderInfo = new OrderInfo(order,orderItems,user.getName());
            orderInfos.add(orderInfo);
        }
        return orderInfos;
    }

    public List<OrderInfo> selectOrder(int payState){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.selectUserByName(username);
        List<Order> orderList = orderMapper.selectOrderByUserId(user.getId());
        ArrayList<OrderInfo> orderInfos = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getPayState() == payState) {
                List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getId());
                OrderInfo orderInfo = new OrderInfo(order, orderItems,user.getName());

                for (OrderItem item : orderItems) {
                    Book book = bookMapper.selectById(item.getProduct_id());
                    item.setBook(book);
                }
                orderInfos.add(orderInfo);
            }
        }
        return orderInfos;
    }
}
