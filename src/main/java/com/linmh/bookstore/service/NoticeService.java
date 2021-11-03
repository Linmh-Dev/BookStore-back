package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.Notice;

import java.util.List;

public interface NoticeService {
    int addNotice(Notice notice);

    int removeNotice(Integer noticeId);

    List<Notice> queryAllNotice();

    int updateNotice(Notice notice);

    Notice newestNotice();
}
