create database livraria;

use livraria;

create table produtos(
	id bigint NULL AUTO_INCREMENT,
	nome varchar(255),
	descricao varchar(255),
	valor varchar(255),
	isbn varchar(255),
	primary key (id));
