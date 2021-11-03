package com.linmh.bookstore.service;

import com.linmh.bookstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        com.linmh.bookstore.bean.User user = userMapper.selectUserByName(username);
        if (user == null) {
            throw new BadCredentialsException("用户不存在");
        }
        if (user.getState() == -1) {
            throw new LockedException("账户已禁用");
        }

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        grantedAuthorities.add(authority);
        return new User(username
                , new BCryptPasswordEncoder().encode(user.getPass())
                , grantedAuthorities);
    }
}
