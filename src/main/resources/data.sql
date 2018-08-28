/* Insert into memory */
insert into memory (id, title, active) values(10001, 'Spring Data mapping  ', true);
insert into memory (id, title, active) values(10002, 'How implement RESTx01', true);
insert into memory (id, title, active) values(10003, 'How implement RESTx02', true);
insert into memory (id, title, active) values(10004, 'How implement RESTx03', true);
insert into memory (id, title, active) values(10005, 'How implement RESTx04', true);
insert into memory (id, title, active) values(10006, 'How implement RESTx05', true);
insert into memory (id, title, active) values(10007, 'How implement RESTx06', true);

/* Insert into memory_content */
insert into memory_content (memory_id, text) values(10001, 'If you want to map a class, you should...');
insert into memory_content (memory_id, text) values(10002, 'This is very good questionx01...');
insert into memory_content (memory_id, text) values(10003, 'This is very good questionx02...');
insert into memory_content (memory_id, text) values(10004, 'This is very good questionx03...');
insert into memory_content (memory_id, text) values(10005, 'This is very good questionx04...');
insert into memory_content (memory_id, text) values(10006, 'This is very good questionx05...');
insert into memory_content (memory_id, text) values(10007, 'This is very good questionx06...');

/* Insert into tag */
insert into tag (id, name, description) values(10001, 'J2EE', 'J2EE');
insert into tag (id, name, description) values(10002, 'J2SE', 'J2SE');
insert into tag (id, name, description) values(10003, 'Java', 'Java');
insert into tag (id, name, description) values(10004, 'MySQL', 'MySQL');
insert into tag (id, name, description) values(10005, 'Rest Api', 'Rest Api');

/* Insert into memory_tag */
insert into memory_tag (memory_id, tag_id) values(10001, 10001);
insert into memory_tag (memory_id, tag_id) values(10001, 10002);
insert into memory_tag (memory_id, tag_id) values(10001, 10003);
insert into memory_tag (memory_id, tag_id) values(10001, 10004);
insert into memory_tag (memory_id, tag_id) values(10002, 10002);
insert into memory_tag (memory_id, tag_id) values(10003, 10003);
insert into memory_tag (memory_id, tag_id) values(10004, 10004);
insert into memory_tag (memory_id, tag_id) values(10005, 10005);





