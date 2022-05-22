/* many-to-many*/

create table client (
	id serial primary key,
	name varchar(255)
);

create table goods (
	id serial primary key,
	name varchar(255),
	price real
);

create table client_goods (
	id serial primary key,
	client_id integer references client(id),
	goods_id integer references goods(id)
);

insert into client(name) values('Vasya');
insert into client(name) values('Petya');

insert into goods(name, price) values ('Apple', 100.00);
insert into goods(name, price) values ('Orange', 150.00);

insert into client_goods(client_id, goods_id) values (1, 1);
insert into client_goods(client_id, goods_id) values (1, 2);
insert into client_goods(client_id, goods_id) values (2, 1);
insert into client_goods(client_id, goods_id) values (2, 2);
