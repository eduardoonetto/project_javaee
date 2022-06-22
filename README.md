# project_javaee
IPCHILE Project 2022

CREATE DATABASE PRU;
USE PRU;
CREATE TABLE usuarios (
id  int auto_increment primary key not null,
nombre varchar(80),
apellido varchar(80),
email varchar(80),
pass varchar(80)
);

insert into usuarios values (default, 'Eduardo', 'Onetto', 'eonetto@gmail.com', '123123');
insert into usuarios values (default, 'Noemi', 'Onetto', 'nonetto@gmail.com', '321321');
