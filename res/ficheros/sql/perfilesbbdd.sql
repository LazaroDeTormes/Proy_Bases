drop database if exists perfilesbbdd;
create database if not exists perfilesbbdd;
use perfilesbbdd;

drop table if exists usuarios;
create table if not exists usuarios (
	id_usr int auto_increment primary key not null,
    nombre varchar(50) unique not null,
    contrasenha varchar(255) not null
    )ENGINE INNODB;
    
    
insert into usuarios (nombre, contrasenha) values ("pepe", "1234");
