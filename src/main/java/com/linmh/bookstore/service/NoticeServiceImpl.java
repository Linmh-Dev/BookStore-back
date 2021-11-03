package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.Notice;
import com.linmh.bookstore.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public int addNotice(Notice notice) {
        notice.setTime(new Date());
        return noticeMapper.insert(notice);
    }

    @Override
    public int removeNotice(Integer noticeId) {
        return noticeMapper.deleteById(noticeId);
    }

    @Override
    public List<Notice> queryAllNotice() {
        return noticeMapper.selectList(null);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateById(notice);
    }

    @Override
    public Notice newestNotice() {
        Notice notice = noticeMapper.getNotice();
        return notice;
    }
}
