package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Book;
import com.linmh.bookstore.bean.Response;
import com.linmh.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CartController {
    public static final String CART_KEY = "CART_KEY";

    @Autowired
    BookService bookService;

    @PostMapping("/cart/{id}")
    public Object addToCart(HttpSession session,@PathVariable String id, HttpServletResponse response){
        Map<String, Book> cart = (Map<String, Book>) session.getAttribute(CART_KEY);
        if (cart == null){
            cart = new LinkedHashMap<>();
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(7*24*60*60);
            response.addCookie(cookie);
            session.setAttribute(CART_KEY,cart);
        }

        Book book = bookService.queryBookById(id);
        if (cart.containsKey(id)){
            return Response.failure("购物车中已存在");
        }

        if (book == null){
            return Response.failure("书籍不存在");
        }
        cart.put(id,book);

        return Response.success(book);
    }

/*    @PutMapping("/cart/{id}/{num}")
    public Object updateNum(HttpSession session,@PathVariable String id,@PathVariable Integer num) {
        Map<String, Book> cart = (Map<String, Book>) session.getAttribute(CART_KEY);
        if (cart == null) {
            return Response.failure("购物车为空");
        }
        for (Map.Entry<String, Book> entry : cart.entrySet()) {
            Book book = entry.getValue();
            if (book.getId().equals(id)){
                book.setNum(num);
                return Response.success(book);
            }
        }
        return Response.failure("购物车中没有（id="+id+"）的书籍");
    }*/

    @GetMapping("/cart")
    public Object queryCart(HttpSession session){
        Map<String, Book> cart = (Map<String, Book>) session.getAttribute(CART_KEY);
        if (cart == null){
            cart = new LinkedHashMap<>();
        }
        List<Book> books = new ArrayList<>();
        for (Map.Entry<String, Book> entry : cart.entrySet()) {
            books.add(entry.getValue());
        }
        return Response.success(books);
    }

    @PutMapping("/cart/{id}/{num}")
    public Object updateCart(@PathVariable String id,@PathVariable Integer num,HttpSession session){
        Map<String, Book> cart = (Map<String, Book>) session.getAttribute(CART_KEY);
        if (cart == null||cart.isEmpty()){
            return Response.failure("购物车为空");
        }

        for (Map.Entry<String, Book> entry : cart.entrySet()) {
            Book book = entry.getValue();
            if (book.getId().equals(id)){
               book.setNum(num);
               return Response.success(book);
           }
        }
        return Response.failure();
    }

    @DeleteMapping("/cart/{bookId}")
    public Object clearCart(HttpSession session,@PathVariable(required = false) String bookId){
        Map<String, Book> cart = (Map<String, Book>) session.getAttribute(CART_KEY);
        if (cart == null||cart.isEmpty()){
            return Response.failure("购物车为空");
        }
        if (bookId==null){
            cart.clear();
        }else{
            cart.remove(bookId);
        }
        return Response.success("清空购物车成功");
    }
}
