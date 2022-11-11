drop database if exists iconosbbdd;
create database if not exists iconosbbdd;

use iconosbbdd;

drop table if exists grupos;
create table if not exists grupos(
id_grupo int not null auto_increment primary key,
nombre_grupo varchar(30)not null unique
)engine innodb;


drop table if exists categorias;
create table if not exists categorias(
id_cat int not null auto_increment primary key,
nombre_cat varchar(30)not null unique
)engine innodb;


drop table if exists iconos;
create table if not exists iconos(

id int not null auto_increment primary key,
nombre varchar(25) not null,
unicodigo varchar(60) not null unique,
grupo varchar(30) not null,
categoria varchar(30) not null,

foreign key (grupo) references grupos (nombre_grupo)
		ON DELETE RESTRICT
        ON UPDATE CASCADE,
        
foreign key (categoria) references categorias (nombre_cat)
		ON DELETE RESTRICT
        ON UPDATE CASCADE

)engine innodb;
