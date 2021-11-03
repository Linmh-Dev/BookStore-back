package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.Book;
import com.linmh.bookstore.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    public int addBook(Book book) {
        book.setId(UUID.randomUUID().toString().replaceAll("-",""));
        Integer insert = bookMapper.insert(book);
        return insert;
    }

    @Override
    public List<Book> queryBookByType(String category) {
        return bookMapper.selectByType(category);
    }

    @Override
    public List<Book> queryBookByName(String bookName) {
        List<Book> list = bookMapper.selectByName(bookName);
        return list;
    }

    @Override
    public Book queryBookById(String id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> queryBookByKey(String key){
        return bookMapper.selectByKey(key);
    }

    @Override
    public List<Book> queryAllBook() {
        return bookMapper.selectList(null);
    }

    @Override
    public int updateBook(Book book) {
        Integer integer = bookMapper.updateById(book);
        return integer;
    }

    @Override
    public int removeBook(String bookId) {
        Integer res = bookMapper.deleteById(bookId);
        return res;
    }

    @Override
    public List<Book> queryHotBook(Integer num) {
        return bookMapper.selectHotSale(num);
    }

    @Override
    public List<Book> queryHotBookList(Long timeMillis) {
        //开始时间
        Date startDate = new Date(timeMillis);
        String startFormat = new SimpleDateFormat("yyy-MM-dd").format(startDate);

        //结束时间
        Calendar calendar = new Calendar.Builder()
                .setInstant(startDate)
                .build();
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
        Date entDate = calendar.getTime();
        String endFormat = new SimpleDateFormat("yyy-MM-dd").format(entDate);
        List<Book> list = bookMapper.selectBookList(startFormat, endFormat);
        
        return list;
    }

}
