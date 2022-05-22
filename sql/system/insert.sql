insert into role(name) values('admin');
insert into role(name) values('user');

insert into rules(name) values('create');
insert into rules(name) values('update');
insert into rules(name) values('delete');

insert into role_rules(role_id, rule_id) values (1, 1);
insert into role_rules(role_id, rule_id) values (1, 2);
insert into role_rules(role_id, rule_id) values (1, 3);
insert into role_rules(role_id, rule_id) values (2, 1);

insert into users(name, role_id) values ('Morpheus', 1);
insert into users(name, role_id) values ('Neo', 2);
insert into users(name, role_id) values ('Trinity', 2);

insert into state(name) values ('to do');
insert into state(name) values ('in progress');
insert into state(name) values ('testing');
insert into state(name) values ('done');
insert into state(name) values ('reopened');

insert into category(name) values('task');
insert into category(name) values('epic');
insert into category(name) values('release');

insert into item(name, user_id, state_id, category_id) values ('Write File Finder', 1, 1, 1);
insert into item(name, user_id, state_id, category_id) values ('Release File Finder', 1, 2, 3);

insert into comments(message, item_id) values ('Very difficult task', 1);

