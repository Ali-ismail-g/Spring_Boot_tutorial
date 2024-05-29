CREATE DATABASE  IF NOT EXISTS `authService`;
USE `authService`;

DROP TABLE IF EXISTS `token`;
DROP TABLE IF EXISTS `user`;

create table user(
					id int auto_increment primary key not null ,
					first_name varchar(255) not null ,
					last_name varchar(255) not null ,
					email varchar(255) not null ,
                    password varchar(255) not null ,
                    role enum('admin','user'),
                    enable boolean not null ,
                    OTP varchar(10) null
);

create table token(
					id int primary key  auto_increment not null ,
					token varchar(255) not null ,
                    token_type enum('BEARER'),
					revoked_token tinyint not null,
					expired_token tinyint not null,
					user_id int not null ,
					foreign key (user_id) references user(id)
);