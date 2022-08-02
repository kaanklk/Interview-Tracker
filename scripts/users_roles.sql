insert into users (admin, created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo, uuid)
values (true, now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e0'),
       (true, now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1'),
	   (false, now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2'),
	   (true, now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3'),
	   (true, now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4'),
	   (false, now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5'),
	   (true, now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e6'),
	   (false, now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e7'),
	   (true, now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e8'),
	   (false, now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10', 'dfb79789-98ff-4709-b2a2-f9a6d760f1r5');

insert into roles (role_name, uuid)
values ('Management Interviewer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1'),
       ('Project Manager', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2'),
       ('Recruiter', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3'),
	   ('Sourcer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4'),
	   ('Technical Interviewer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5');

insert into users_roles (uuid, user_id, project_id)
values ('ggg79789-98ff-4709-b2a2-f9a6d760f1e2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1', 'ggb79789-98ff-4709-b2a2-f9a6d760f1e1'),
       ('hhh79789-98ff-4709-b2a2-f9a6d760f1e2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2', 'klh79789-98ff-4709-b2a2-f9a6d760f1e2'),
       ('ppp79789-98ff-4709-b2a2-f9a6d760f1e2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3', 'ikl79789-98ff-4709-b2a2-f9a6d760f1e2'),
	   ('qwe79789-98ff-4709-b2a2-f9a6d760f1e2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4', 'asd79789-98ff-4709-b2a2-f9a6d760f1e2'),
	   ('azs79789-98ff-4709-b2a2-f9a6d760f1e2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5', 'vbc79789-98ff-4709-b2a2-f9a6d760f1e2');

insert into users_roles_role (user_roles_id, role_id)
values (1,2),
       (1,3),
       (2,5),
	   (3,1),
	   (4,4);