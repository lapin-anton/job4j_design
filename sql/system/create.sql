create table role (
	id serial primary key,
	name varchar(128)
);

create table rules (
	id serial primary key,
	name varchar(255)
);

create table role_rules (
	id serial primary key,
	role_id integer references role(id),
	rule_id integer references rules(id)
);

create table users (
	id serial primary key,
	name varchar(255),
	role_id integer references role(id)
);

create table state (
	id serial primary key,
	name varchar(128)
);

create table category (
	id serial primary key,
	name varchar(128)
);

create table item (
	id serial primary key,
	name varchar(255),
	user_id integer references users(id),
	state_id integer references state(id),
	category_id integer references category(id)
);

create table comments (
	id serial primary key,
	message text,
	item_id integer references item(id)
);

create table attachs (
	id serial primary key,
	file bytea,
	item_id integer references item(id)
);
