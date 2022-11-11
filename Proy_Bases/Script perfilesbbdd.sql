drop database if exists perfilesbbdd;
create database if not exists perfilesbbdd;

use perfilesbbdd;

drop table if exists usuarios;
create table if not exists usuarios(
id_usuario int not null auto_increment primary key,
nombre varchar(10)not null unique,
contrasenha varchar(250) not null 
)engine innodb;