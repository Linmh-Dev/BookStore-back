package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Book;
import com.linmh.bookstore.bean.Response;
import com.linmh.bookstore.config.ResourceConfig;
import com.linmh.bookstore.service.BookService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class BookController {
    @Autowired
    ResourceConfig resourceConfig;

    @Autowired
    BookService bookService;

    @Secured("ROLE_Admin")
    @PostMapping("/book")
    public Object addBook(@RequestBody Book book) {
        System.out.println(book.toString());
        int res = bookService.addBook(book);
        if (res > 0) {
            return Response.success();
        }
        return Response.failure();
    }
    @Secured("ROLE_Admin")
    @PutMapping("/book")
    public Object updateBook(@RequestBody Book book){
        int res = bookService.updateBook(book);
        if (res<0){
            return Response.failure();
        }
        return Response.success();
    }

    @GetMapping("/book/{category}")
    public Object queryBook(@PathVariable("category") String category) {

        List<Book> books = bookService.queryBookByType(category);
        if (books == null) {
            return Response.failure();
        }
        if (books.isEmpty()) {
            return Response.failure("没有找到和" + category + "相关书籍");
        }
        return Response.success(books);
    }

    @GetMapping("/book")
    public Object queryAllBook() {
        List<Book> books = bookService.queryAllBook();
        if (books == null||books.isEmpty()) {
            return Response.failure();
        }
        return Response.success(books);
    }

    @Secured("ROLE_Admin")
    @DeleteMapping("book/{bookId}")
    public Object removeBook(@PathVariable String bookId){
        int res = bookService.removeBook(bookId);
        if (res<0){
            return Response.failure();
        }
        return Response.success();
    }

    @GetMapping("/searchBook/{bookName}")
    public Object queryBookByName(@PathVariable("bookName") String bookName) {

        List<Book> books = bookService.queryBookByName(bookName);
        if (books == null) {
            return Response.failure();
        }
        if (books.isEmpty()) {
            return Response.failure("没有找到《" + bookName + "》");
        }
        return Response.success(books.get(0));
    }


    @PostMapping("bookCover")
    public Object uploadCover(@RequestParam("cover") MultipartFile coverImage) {
        String imageDir = resourceConfig.getImageDir();
        String coverName = UUID.randomUUID().toString() + coverImage.getOriginalFilename();
        File coverFile = new File(imageDir + coverName);
        try {
            coverImage.transferTo(coverFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "/" + resourceConfig.getImageMapUrl().replaceAll("\\*", "");
        return Response.success(str + coverName);
    }


    @GetMapping("/bookKey/{key}")
    public Object queryBookByKey(@PathVariable String key) {
        if (key == null) {
            return Response.failure();
        }

        List<Book> books = bookService.queryBookByKey(key);
        return Response.success(books);
    }

}
