/*1*/
create table departments (
	id serial primary key,
	name varchar(255)
);

create table employees (
	id serial primary key,
	name varchar(255),
	dep_id int references departments(id)
);

insert into departments(name) values ('Development'), ('Testing'), ('Analyst'), ('Management'), ('DevOps');
insert into employees(name, dep_id) values
	('John', 1),
	('Mark', 1),
	('Steve', 2),
	('Anna', 2),
	('Alex', 3),
	('Leo', 3),
	('Sheldon', 4),
	('Govard', 4),
	('Rajesch', null),
	('Penny', null);

/*2*/
select * from employees e
	left join departments d on d.id = e.dep_id;

select * from departments d
	left join employees e on e.dep_id = d.id;

select * from employees e
	right join departments d on d.id = e.dep_id;

select * from departments d
	right join employees e on e.dep_id = d.id;

select * from employees e
	full join departments d on d.id = e.dep_id;

select * from departments d
	full join employees e on e.dep_id = d.id;

select * from employees e
	cross join departments;
/*3*/
select * from departments d
	left join employees e on e.dep_id = d.id
	where e is null;
/*4*/
select d.id, d.name, e.id, e.name, e.dep_id from departments d
	left join employees e on e.dep_id = d.id;

select d.id, d.name, e.id, e.name, e.dep_id from employees e
	right join departments d on d.id = e.dep_id;
/*5*/
create table teens (
	id serial primary key,
	name varchar(255),
	gender varchar(1)
);

insert into teens(name, gender) values
	('Sheldon', 'M'),
	('Leo', 'M'),
	('Govard', 'M'),
	('Rajesh', 'M'),
	('Penny', 'F'),
	('Amy', 'F'),
	('Bernadeth', 'F'),
	('Lucy', 'F');

select t1.name as male, t2.name as female from teens t1
	cross join teens t2 where t1.gender = 'M' and t2.gender = 'F';