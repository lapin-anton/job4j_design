select p.name from product p
	join type t on t.id = p.type_id
	where t.name = 'СЫР';

select * from product where name like '%мороженое%';

select * from product where expired_date < now();

select * from product where price in (select max(price) from product);

select t.name, count(p) from product p
	join type t on t.id = p.type_id
	group by t.name;

select * from product p
	join type t on t.id = p.type_id
	where t.name in ('МОЛОКО', 'СЫР');

select t.name from type t
	join product p on p.type_id = t.id
	group by t.name
	having count(p) < 10;

select p.name, p.expired_date, p.price, t.name from product p
	left join type t on t.id = p.type_id;