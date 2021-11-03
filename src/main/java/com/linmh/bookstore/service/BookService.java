package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.Book;

import java.util.List;

public interface BookService {
    int addBook(Book book);

    List<Book> queryBookByType(String category);

    List<Book> queryBookByName(String bookName);

    Book queryBookById(String id);

    List<Book> queryBookByKey(String key);

    List<Book> queryAllBook();

    int updateBook(Book book);

    int removeBook(String bookId);

    List<Book> queryHotBook(Integer num);

    List<Book> queryHotBookList(Long timeMillis);
}
