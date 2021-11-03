package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.User;

import java.util.List;

public interface UserService {
    boolean login(User user);

    boolean addUser(User user);

    int deleteUser(Integer userId);

    User getUserInfo();

    int updateUser(User user);

    int updateAvatar(String avatarUrl);

    List<User> getAllUser();
}
