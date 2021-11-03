package com.linmh.bookstore;

import com.linmh.bookstore.bean.*;
import com.linmh.bookstore.mapper.AddressMapper;
import com.linmh.bookstore.mapper.BookMapper;
import com.linmh.bookstore.mapper.OrderMapper;
import com.linmh.bookstore.mapper.UserMapper;
import com.linmh.bookstore.service.BookService;
import com.linmh.bookstore.service.NoticeService;
import com.linmh.bookstore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BookService bookService;
    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        User user = new User("linmh", "ddd", "dfd",
                "1", "dfd", "ddd", new Date());
        User user1 = userMapper.selectUserByName(user.getName());
        System.out.println(user1.toString());
    }

    @Test
    void addBook() {
  /*      Book book = new Book("4311","第一行代码",99.0,"计算机",100,"/static/image/b3f89d35-a08a-46b8-a9f1-3ed9d6bfa03b头像.jpg","erwer");
        bookService.addBook(book);*/
        Book book = bookService.queryBookById("4315");
        System.out.println(book);
    }

    @Test
    void orderTest() {
        Order order = new Order();
        order.setMoney(100.0);
        order.setUserId(4311);
        order.setReceiverAddress("广西贺州");
        order.setReceiverName("linmh");
        order.setReceiverPhone("13557476516");
        // orderService.addOrder(order);

    }

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    NoticeService noticeService;
    @Autowired
    ServletContext servletContext;

    @Test
    void orderTest1() throws ParseException {
/*        Notice notice = new Notice();
        notice.setDetails("dfjdfjdof\nfjdfjodsf\nfjdfjosdfjo\ndfjojf");
        notice.setTitle("AAAAA");
        noticeService.addNotice(notice);
       // noticeService.removeNotice(1);
        List<Notice> notices = noticeService.queryAllNotice();
        System.out.println(notices);*/

        Date date = new Date(1633017600000L);
        int i = Calendar.getInstance().get(Calendar.MONTH);
        System.out.println(i);
        Calendar calendar = new Calendar.Builder()
                .setInstant(date)
                .build();
        calendar.set(Calendar.MONTH,i+1);
        Date time = calendar.getTime();

        String format = new SimpleDateFormat("yyy-MM-dd").format(date);
        String end = new SimpleDateFormat("yyy-MM-dd").format(time);
        System.out.println(format);
        List<Book> list = bookMapper.selectBookList(format,end);
        System.out.println(list.size());

        //System.out.println(bookMapper.selectBookList());


    }

    @Autowired
    AddressMapper addressMapper;
    @Test
    void avatarTest() {
        int bbb = addressMapper.deleteByUsername("bbb");
        System.out.println("result: "+bbb);
    }

}
