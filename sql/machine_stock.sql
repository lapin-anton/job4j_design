create table body (
	id serial primary key,
	type varchar(255)
);

create table engine (
	id serial primary key,
	type varchar(255),
	volume float,
	cylinder_count int,
	power float
);

create table transmission (
	id serial primary key,
	type varchar(255),
	speeds int
);

create table machine (
	id serial primary key,
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(type) values ('Седан'), ('Хэтчбек'), ('Пикап'), ('Минивэн'), ('Фургон'), ('Кабриолет');
insert into engine(type, volume, cylinder_count, power) values
	('Дизельный', 3.5, 6, 250.0),
	('Дизельный', 2.0, 4, 138.0),
	('Бензиновый', 3.5, 6, 350.0),
	('Бензиновый', 2.5, 8, 251.0),
	('Дизельный', 4.5, 12, 535.0);
insert into transmission(type, speeds) values
	('Ручная', 4),
	('Автоматическая', 6),
	('Ручная', 5),
	('Автоматическая', 5),
	('Ручная', 10);
insert into machine(body_id, engine_id, transmission_id) values
	(1, 1, 1),
	(1, 2, 3),
	(2, 3, null),
	(2, 4, 2),
	(3, null, 4),
	(3, 1, null),
	(4, 2, 1),
	(4, 3, 3),
	(5, 4, null),
	(5, null, 2),
	(null, 1, 4),
	(1, null, null),
	(null, 2, null),
	(null, null, 3);

select * from machine m
	left join body b on b.id = m.body_id
	left join engine e on e.id = m.body_id
	left join transmission t on t.id = m.transmission_id;

select * from body b
	left join machine m on m.body_id = b.id
	where m is null;

select * from engine e
	left join machine m on m.engine_id = e.id
	where m is null;

select * from transmission t
	left join machine m on m.transmission_id = t.id
	where m is null;