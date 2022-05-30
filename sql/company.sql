CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values
	(1, 'Рога и Копыта'),
	(2, 'ИП Лавочкин'),
	(3, 'Кондрат и Сын'),
	(4, 'Microsoft'),
	(5, 'Apple');

insert into person(id, name, company_id) values
	(1, 'A', 1),
	(2, 'B', 1),
	(3, 'C', 2),
	(4, 'D', 2),
	(5, 'E', 3),
	(6, 'F', 3),
	(7, 'G', 4),
	(8, 'H', 4),
	(9, 'I', 5),
	(10, 'K', 5),
	(11, 'L', 1),
	(12, 'M', 1),
	(13, 'N', 1),
	(14, 'O', 2),
	(15, 'P', 2),
	(16, 'Q', 2),
	(17, 'R', 3),
	(18, 'S', 3),
	(19, 'T', 3),
	(20, 'U', 4);

select name from person where company_id <> 5;

select p.name as person, c.name as company from company c
	join person p on p.company_id = c.id;

select c.name as company, count(c.name) as persons from company c
	join person p on p.company_id = c.id
	group by c.name
	having count(c.name) = (select count(p.company_id) as c from person p
		group by p.company_id
		order by c desc	limit 1);