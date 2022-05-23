create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iPhone', 7000), ('iPad', 10000), ('iPod', 3000), ('KPK', 4500);
insert into people(name) values ('Геродот'), ('Аристотель'), ('Гераклит');
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 2), (4, 2), (2, 3), (4, 3);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people dp
	join people p on p.id = dp.people_id
	join devices d on d.id = dp.device_id
	group by p.name;

select p.name, avg(d.price) from devices_people dp
	join people p on p.id = dp.people_id
	join devices d on d.id = dp.device_id
	group by p.name
	having avg(d.price) > 5000;