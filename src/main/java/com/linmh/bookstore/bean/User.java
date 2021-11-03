package com.linmh.bookstore.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    public User(String name, String email, String pass, String sex, String telephone, String desc, Date date) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.gender = sex;
        this.telephone = telephone;
        this.introduce = desc;
        this.registTime = date;
    }

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("email")
    private String email;
    @TableField("role")
    private String role;
    @TableField("pass")
    private String pass;
    @TableField("gender")
    private String gender;
    @TableField("telephone")
    private String telephone;
    @TableField("introduce")
    private String introduce;
    @TableField("avatar")
    private String avatar;
    @TableField("state")
    private Integer state;
    @TableField("registTime")
    private Date registTime;

    private transient String validateCode;

}
