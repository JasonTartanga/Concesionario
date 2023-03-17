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
    dni_propietario	char(9),
    
    constraint pk_coche primary key (matricula),
    constraint fk_coche foreign key (dni_propietario) references usuario(dni)
);
