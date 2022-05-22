/*one-to-one*/
create table person (
	id serial primary key,
	name varchar(255)
);

create table credentials (
	id serial primary key,
	login varchar(255),
	password varchar(20)
);

create table user_credentials (
	id serial primary key,
	person_id integer references person(id) unique,
	cred_id integer references credentials(id) unique
);

insert into person(name) values ('Petya');
insert into person(name) values ('Kolya');

insert into credentials(login, password) values ('petr', '1234');
insert into credentials(login, password) values ('nick', '5678');

insert into user_credentials(person_id, cred_id) values (1, 1);
insert into user_credentials(person_id, cred_id) values (2, 2);