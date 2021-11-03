package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Book;
import com.linmh.bookstore.bean.Response;
import com.linmh.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class HotSaleController {
    @Autowired
    BookService bookService;


    @GetMapping("/hotSale/{num}")
    public Object getHotSale(@PathVariable(required = false) Integer num) {
        if (num == null) {
            num = 2;
        }
        List<Book> list = bookService.queryHotBook(num);
        if (list == null || list.isEmpty()) {
            return Response.failure();
        }
        return Response.success(list);
    }

    @GetMapping("/hotSaleList")
    public Object hot(HttpServletResponse response,@RequestParam("date") Long dateTime) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/csv");
        Date startDate = new Date(dateTime);
        String startFormat = new SimpleDateFormat("yyyy年MM月").format(startDate);

        response.setHeader("Content-Disposition", "attachment;filename="
                + URLEncoder.encode(startFormat+"销售数据.csv","UTF-8"));

        StringBuilder sb = new StringBuilder();

        List<Book> list = bookService.queryHotBookList(dateTime);
        sb.append("商品名称,销售数量\n");
        for (Book book : list) {
            sb.append(book.getName() + "," + book.getNum() + "\n");
        }

        return sb.toString();
    }


}
