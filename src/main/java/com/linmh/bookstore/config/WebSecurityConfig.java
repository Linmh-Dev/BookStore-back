package com.linmh.bookstore.config;

import com.alibaba.fastjson.JSON;
import com.linmh.bookstore.bean.Response;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/address", "/cart/**", "/order/**", "book/**")
                .authenticated()
                .and()
                .exceptionHandling()

                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.setContentType("text/html;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(JSON.toJSONString(Response.failure(403, "没有权限")));

                    }
                })
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.setContentType("text/html;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(JSON.toJSONString(Response.failure(402, "未登录")));

                    }
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.setContentType("text/html;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(JSON.toJSONString(Response.success(SecurityContextHolder.getContext().getAuthentication().getPrincipal())));

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.setContentType("text/html;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        Object cause = e.getCause();
                        if (cause instanceof BadCredentialsException) {
                            writer.write(JSON.toJSONString(Response.failure(500, e.getMessage())));
                        } else if (cause instanceof LockedException) {
                            writer.write(JSON.toJSONString(Response.failure(501, "账号已禁用")));
                        } else {
                            writer.write(JSON.toJSONString(Response.failure(500, "密码错误")));
                        }

                    }
                })
                .and()
                .logout()
                .logoutUrl("/out")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = null;
                        try {
                            writer = httpServletResponse.getWriter();
                            writer.write(JSON.toJSONString(Response.success(401, "退出登录成功")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(jdbcTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60 * 7)
                .and()
                .cors()
                .disable()
                .csrf()
                .disable();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/hotSaleList");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
