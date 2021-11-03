package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Book;
import com.linmh.bookstore.bean.Order;
import com.linmh.bookstore.bean.OrderInfo;
import com.linmh.bookstore.bean.Response;
import com.linmh.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    public static final String CART_KEY = "CART_KEY";
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public Object addOrder(@RequestBody Order order, HttpSession session) {
        Map<String, Book> cart = (Map<String, Book>) session.getAttribute(CART_KEY);
        if (cart == null || cart.isEmpty()) {
            return Response.failure("购物车没有商品");
        }
        int res = orderService.addOrder(order,cart);
        if (res < 0) {
            return Response.failure();
        }
        return Response.success(order);
    }

    @GetMapping("/order/{orderId}")
    public Object queryOrder(@PathVariable String orderId) {
        Order order = orderService.queryOrder(orderId);
        if (order == null) {
            return Response.failure("订单不存在");
        }

        return Response.success(order);
    }

    @GetMapping("/order")
    public Object queryAllOrder() {
        List<OrderInfo> list = orderService.queryAllOrder();
        if (list == null){
            return Response.failure();
        }
        return Response.success(list);
    }

    @PutMapping("/order")
    public Object payOrder(@RequestBody Order postOrder) {
        System.out.println(postOrder);
        Order order = orderService.queryOrder(postOrder.getId());
        if (order == null) {
            return Response.failure("订单不存在");
        }
        int res = orderService.activeOrder(postOrder);
        if (res < 0) {
            return Response.failure();
        }
        return Response.success("付款成功", orderService.queryOrder(postOrder.getId()));
    }

    @Secured("ROLE_Admin")
    @PutMapping("/deliver/{orderId}")
    public Object deliver(@PathVariable String orderId) {
        int res = orderService.deliverOrder(orderId);
        if (res<0){
            return Response.failure();
        }
        return Response.success();
    }

    @DeleteMapping("order/{orderId}")
    public Object removeOrder(@PathVariable String orderId) {
        int res = orderService.removeOrder(orderId);
        if (res<0){
            return Response.failure();
        }
        return Response.success("删除订单成功");
    }

    @GetMapping("/orderInfo/{payState}")
    public Object getOrders(@PathVariable Integer payState) {
        if (payState == null) {
            return Response.failure();
        }
        List<OrderInfo> orderInfos = orderService.selectOrder(payState);
        return Response.success(orderInfos);
    }

}
