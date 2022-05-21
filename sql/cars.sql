create table cars (
	id serial primary key,
	brand varchar(256),
	country text,
	founded date,
	rating integer
);

insert into cars(brand, country, founded, rating) values ('Tesla', 'USA', '15-01-2007', 1);

select * from cars;

update cars set brand = 'Lada';

delete from cars;