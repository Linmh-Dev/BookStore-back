package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Response;
import com.linmh.bookstore.bean.User;
import com.linmh.bookstore.config.ResourceConfig;
import com.linmh.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController()
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    ResourceConfig resourceConfig;

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public Object register(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user);

        String validateCode = (String) request.getSession().getAttribute(ValidateCodeController.VALIDATE_CODE_KEY);
        if (validateCode == null || !validateCode.equals(user.getValidateCode())) {
            return Response.failure(501, "验证码错误");
        }
        user.setRegistTime(new Date());
        boolean b = userService.addUser(user);
        if (!b) {
            return Response.failure();
        }
        return Response.success();
    }

    @GetMapping("/user")
    public Object getUserInfo() {
        User userInfo = userService.getUserInfo();
        if (userInfo != null) {
            return Response.success(userInfo);
        } else {
            return Response.failure();
        }
    }

    @GetMapping("/user/all")
    public Object getAllUser() {
        List<User> users = userService.getAllUser();
        if (users != null && !users.isEmpty()) {
            return Response.success(users);
        } else {
            return Response.failure();
        }
    }

    @PutMapping("/user")
    public Object updateUser(@RequestBody User user) {
        int res = userService.updateUser(user);
        if (res < 0) {
            return Response.failure();
        }
        return Response.success();
    }

    @PostMapping("/avatar")
    public Object uploadCover(@RequestParam("avatar") MultipartFile coverImage) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String imageDir = resourceConfig.getImageDir();
        String avatarName = UUID.randomUUID().toString() + coverImage.getOriginalFilename();
        File coverFile = new File(imageDir + avatarName);
        try {
            coverImage.transferTo(coverFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "/" + resourceConfig.getImageMapUrl().replaceAll("\\*", "");
        str = str + avatarName;

        return Response.success(str);
    }

    @DeleteMapping("/user/{userId}")
    public Object deleteUser(@PathVariable("userId") Integer userId) {
        int res = userService.deleteUser(userId);
        if (res < 0) {
            return Response.failure();
        }
        return Response.success();
    }
}
