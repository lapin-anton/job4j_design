create table country (
	id serial primary key,
	name varchar(255)
);

create table city (
	id serial primary key,
	name varchar(255),
	country_id integer references country(id)
);

insert into country(name) values ('Russia');
insert into country(name) values ('USA');
insert into country(name) values ('Germany');

insert into city(name, country_id) values ('Moscow', 1);
insert into city(name, country_id) values ('St. Petersburg', 1);

insert into city(name, country_id) values ('New York', 2);
insert into city(name, country_id) values ('Chicago', 2);

insert into city(name, country_id) values ('Berlin', 3);
insert into city(name, country_id) values ('Koeln', 3);

select cn.name as Country, ct.name as City from country as cn
	join city as ct on ct.country_id = cn.id;

select cn.name as Country, ct.name as City from country as cn
	join city as ct on ct.country_id = cn.id where cn.id = 1;

select cn.name as Country, ct.name as City from country as cn
	join city as ct on ct.country_id = cn.id where ct.name like '%n';