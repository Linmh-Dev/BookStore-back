CREATE TABLE user(
id int(11) not null PRIMARY KEY auto_increment,
`name` VARCHAR(20) not NULL,
`pass` varchar(20) not null,
gender varchar(2),
email varchar(50),
telephone VARCHAR(20),
introduce varchar(100),
activeCode VARCHAR(50),
state int(11) default 0,
role  VARCHAR(10) default "普通用户",
registTime TIMESTAMP not null
)character SET utf8;

CREATE TABLE products(
id VARCHAR(100) not null PRIMARY KEY,
`name` VARCHAR(40),
price DOUBLE,
category VARCHAR(40),
pnum int(11),
imgurl VARCHAR(100),
description VARCHAR(255)
)CHARACTER SET utf8;

CREATE TABLE orders(
id VARCHAR(100) not null PRIMARY KEY,
money DOUBLE,
receiverAddress VARCHAR(255),
receiverName VARCHAR(20),
receiverPhone VARCHAR(20),
paystate int(11) default 0,
ordertime TIMESTAMP,
user_id int(11)
)CHARACTER SET utf8;

CREATE TABLE orderitem(
order_id VARCHAR(100) not null PRIMARY KEY,
product_id VARCHAR(100) not null,
buynum int(11)
)CHARACTER set utf8;

CREATE TABLE notice(
nId int not null PRIMARY KEY auto_increment,
title VARCHAR(10),
details VARCHAR(255),
nTime timestamp
)CHARACTER set utf8;

create table address(
`name` varchar(20) not null primary key,
username varchar(20) not null,
province varchar(100) not null,
city varchar(100) not null,
district varchar(100) not null,
detail varchar(255) not null,
phone varchar(15) not null,
`desc` varchar(255)
)character set utf8;

  image-dir: /Users/linmh/Documents/source/
  image-dir: /root/bookstore/