/* many-to-one*/

create table category (
	id serial primary key,
	name varchar(255)
);

create table product (
	id serial primary key,
	name varchar(255),
	category_id integer references category(id),
	in_stock boolean
);

insert into category(name) values ('DIY');

insert into product(name, category_id, in_stock) values ('Constructor', 1, true);