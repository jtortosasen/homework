--1
create database tienda;
--2
show databases;
--3
use tienda;
--4
create table 
	FABRICANTES (Clave_fabricante int not null,
				 Nombre varchar(30) not null,
                 primary key (Clave_fabricante));
create table 
	ARTICULOS (Clave_articulo int not null,
				 Nombre varchar(30) not null,
                 Precio int not null,
                 Clave_fabricante int,
                 primary key (Clave_articulo),
                 foreign key (Clave_fabricante) references FABRICANTES(Clave_fabricante));
--5
show tables;
--6
describe ARTICULOS;
--7
insert into FABRICANTES(Clave_fabricante, nombre) values(1, 'kingston');
insert into FABRICANTES(Clave_fabricante, nombre) values(2, 'Adata');
insert into FABRICANTES(Clave_fabricante, nombre) values(3, 'Logitech');
insert into FABRICANTES(Clave_fabricante, nombre) values(4, 'Lexar');
insert into FABRICANTES(Clave_fabricante, nombre) values(5, 'Seagate');

insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(1, 'Teclado', 100, 3);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(2, 'Disco duro 300 Gb', 500, 5);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(3, 'Mouse', 80, 3);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(4, 'Memoria USB', 140, 4);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(5, 'Memoria RAM', 290, 1);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(6, 'Disco duro extraible 250 GB', 650, 5);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(7, 'Memoria USB', 279, 1);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(8, 'DVD Rom', 450, 2);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(9, 'CD Rom', 200, 2);
insert into  ARTICULOS(Clave_articulo , nombre, precio, Clave_fabricante) values(10, 'Tarjeta de red', 180, 3);
--8
--a
select * from ARTICULOS;
--b
select Nombre from ARTICULOS;
--c
select Nombre, Precio from ARTICULOS;
--d
select distinct Nombre from ARTICULOS;
--e
select * from ARTICULOS where Clave_articulo = 5;
--f
select * from ARTICULOS where Nombre = 'Teclado';
--g
select * from ARTICULOS where Nombre = 'Memoria RAM' OR Nombre = 'Memoria USB';
--h
select * from ARTICULOS where Nombre LIKE 'M%';
--i
select * from ARTICULOS where Precio = 100;
--j
select * from ARTICULOS where Precio > 200;
--k
select * from ARTICULOS where Precio > 100 AND Precio < 350;
--l
select avg(Precio) from ARTICULOS;
--m
select avg(Precio) from ARTICULOS where Clave_fabricante = 2;
--n
select Nombre, Precio from ARTICULOS ORDER BY(Nombre);
--o
select * from ARTICULOS ORDER BY(Precio)desc;
--p
select Nombre, Precio from ARTICULOS where Precio > 250 order by Precio desc, Nombre asc;
--q
select Clave_articulo, ARTICULOS.Nombre, Precio, FABRICANTES.Clave_fabricante, FABRICANTES.Nombre from ARTICULOS
join FABRICANTES on ARTICULOS.Clave_fabricante = FABRICANTES.Clave_fabricante;
--r
select Clave_articulo, ARTICULOS.Nombre, FABRICANTES.Nombre from ARTICULOS
join FABRICANTES on ARTICULOS.Clave_fabricante = FABRICANTES.Clave_fabricante
Where ARTICULOS.Precio is not null;
--s
select ARTICULOS.Nombre, ARTICULOS.Precio from ARTICULOS
join FABRICANTES on ARTICULOS.Clave_fabricante = FABRICANTES.Clave_fabricante
where FABRICANTES.Nombre = 'Logitech' order by ARTICULOS.Nombre;
--t
select ARTICULOS.Nombre, ARTICULOS.Precio, FABRICANTES.Nombre from ARTICULOS
join FABRICANTES on ARTICULOS.Clave_fabricante = FABRICANTES.Clave_fabricante
Where FABRICANTES.Nombre = 'Lexar' OR FABRICANTES.Nombre = 'Kingston' order by ARTICULOS.Precio desc;
--u
insert into ARTICULOS(Clave_articulo, Nombre, Precio, Clave_fabricante) values(11, 'Altavoces', 120, 2);
--v
update ARTICULOS set ARTICULOS.Nombre = 'Impresora Laser' where ARTICULOS.Clave_articulo = 6;
--w
update ARTICULOS set ARTICULOS.Precio = (ARTICULOS.Precio - (ARTICULOS.Precio/100)*10) where ARTICULOS.Precio > 300;
--x
update ARTICULOS set ARTICULOS.Precio = (ARTICULOS.Precio - 10) where ARTICULOS.Precio > 300;
--y
delete from ARTICULOS where ARTICULOS.Clave_articulo = 6;