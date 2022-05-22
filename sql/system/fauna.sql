drop table fauna;

create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('horse', 25322, '1956-01-01');
insert into fauna(name, avg_age, discovery_date) values ('bear', 15780, null);
insert into fauna(name, avg_age, discovery_date) values ('cat', 1301, '1901-11-18');
insert into fauna(name, avg_age, discovery_date) values ('dog', 1766, '1865-06-07');
insert into fauna(name, avg_age, discovery_date) values ('caw', 8000, null);
insert into fauna(name, avg_age, discovery_date) values ('fish', 363718, '1834-08-04');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date <= '1950-01-01';