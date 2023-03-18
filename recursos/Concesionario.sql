DROP DATABASE IF EXISTS CONCESIONARIO;
CREATE DATABASE CONCESIONARIO;
USE CONCESIONARIO;

DROP TABLE IF EXISTS USUARIO;
CREATE TABLE USUARIO(
	nombre			varchar(25)		not null,
    contrasenia		varchar(25)		not null,
    dni				char(9),
    telefono		integer(9)		not null,
    fecha_nac		date			not null,
    genero			char(1)			not null	check(genero in ('H', 'M')),
    titulacion		enum			("ESO", "Bachiller", "Formacion Profesional", "Universidad"),
    
    constraint pk_usuario primary key (dni)
);

DROP TABLE IF EXISTS COCHE;
CREATE TABLE COCHE(
	matricula		char(7),
    marca			varchar(25)		not null,
    modelo			varchar(25)		not null,
    edad			integer			not null,
    precio			float			not null,
    
    constraint pk_coche primary key (matricula)
);

DROP TABLE IF EXISTS PERTENECE;
CREATE TABLE PERTENECE(
	dni			char(9),
    matricula	char(7),
    
    constraint pk_pertenece primary key (dni, matricula),
    constraint fk_pertenece foreign key (dni) references usuario(dni) on delete cascade,
    constraint fk_pertenece1 foreign key (matricula) references coche(matricula) on delete cascade
);


