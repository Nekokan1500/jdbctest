CREATE DATABASE jdbctest;

USE jdbctest;

CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100),
    dob DATE,
    photo BLOB
);

INSERT INTO customer (name, email, dob)
VALUES ('Wang Feng', 'wf@126.com', '2010-02-02'),
('Wang Fei', 'wangf@163.com', '1988-12-26'),
('Lin Zhiling', 'linzl@gmail.com', '1984-06-12');

CREATE TABLE t_order (
    order_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    order_name VARCHAR(50),
    order_date DATE
);

INSERT INTO t_order (order_name, order_date)
VALUES ('AA', '2010-03-04'),
('BB', '2000-02-01'),
('GG', '1994-06-28');

CREATE TABLE t_user (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    password VARCHAR(50),
    address VARCHAR(100),
    phone VARCHAR(20)
);

INSERT INTO t_user (name, password, address, phone)
VALUE ('Zhang Ziyi', 'fast1234', 'Beijing', '13788658672'),
('Guo Fucheng', 'fast1234', 'Hong Kong', '15678909898'),
('Lin Zhiying', 'fast1234', 'Taiwan', '18612124565'),
('Liang Jingru', 'fast1234', 'Malaysia', '18912340998'),
('Lady Gaga', 'fast1234', 'USA', '13012386565');

