package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.User;
import com.linmh.bookstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(User user) {
        User u = userMapper.selectUserByName(user.getName());
        if (u == null) return false;
        if (user.getName().equals(u.getName()) && user.getPass().equals(user.getPass())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public int deleteUser(Integer userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public User getUserInfo() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.selectUserByName(userName);
        return user;
    }

    @Override
    public int updateUser(User user) {
        userMapper.updateById(user);
        return 0;
    }

    @Override
    public int updateAvatar(String avatarUrl) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userMapper.updateAvatar(userName, avatarUrl);
    }

    @Override
    public List<User> getAllUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<User> users = userMapper.selectList(null);
        users = users.stream().filter(
                user -> {
                    if (user.getName().equals(userName)) {
                        return false;
                    }
                    if (user.getName().toLowerCase().equals("admin")) {
                        return false;
                    }
                    return true;
                }
        ).collect(Collectors.toList());
        return users;
    }
}
