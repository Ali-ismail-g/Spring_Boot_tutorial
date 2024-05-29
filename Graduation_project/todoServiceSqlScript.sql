CREATE DATABASE  IF NOT EXISTS `todoService`;
USE `todoService`;

DROP TABLE IF EXISTS `item`;
DROP TABLE IF EXISTS `item_details`;

create table item_details(
					id int auto_increment primary key not null ,
					description varchar(255) not null ,
					created_at date not null ,
					priority enum('high','low') not null ,
                    todo_status enum('completed','not_completed') not null 
);
create table item(
					id int auto_increment primary key not null ,
					title varchar(255) not null ,
					user_id varchar(25) ,
					item_details_id int not null ,
					foreign key (item_details_id) references item_details(id) 
);