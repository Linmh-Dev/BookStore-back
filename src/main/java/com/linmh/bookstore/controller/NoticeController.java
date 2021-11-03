package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Notice;
import com.linmh.bookstore.bean.Response;
import com.linmh.bookstore.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeController {
    @Autowired
    NoticeService noticeService;


    @GetMapping("notice")
    public Object newestNotice() {
        Notice notice = noticeService.newestNotice();
        if (notice == null) {
            return Response.failure();
        }
        return Response.success(notice);
    }


    @GetMapping("/noticeAll")
    public Object noticeAll() {
        List<Notice> notices = noticeService.queryAllNotice();
        if (notices == null) {
            return Response.failure();
        }
        return Response.success(notices);
    }
    @Secured("ROLE_Admin")
    @PostMapping("notice")
    public Object addNotice(@RequestBody Notice notice) {
        int res = noticeService.addNotice(notice);
        if (res < 0) {
            return Response.failure();
        }
        return Response.success();
    }
    @Secured("ROLE_Admin")
    @PutMapping("notice")
    public Object updateNotice(@RequestBody Notice notice) {
        int res = noticeService.updateNotice(notice);
        if (res < 0) {
            return Response.failure();
        }
        return Response.success();
    }
    @Secured("ROLE_Admin")
    @DeleteMapping("/notice/{noticeId}")
    public Object deleteNotice(@PathVariable Integer noticeId) {
        int res = noticeService.removeNotice(noticeId);
        if (res < 0) {
            return Response.failure();
        }
        return Response.success();
    }


    @Secured("ROLE_Admin")
    @GetMapping("/aa")
    public Object test() {
        return Response.success("test");
    }
}
