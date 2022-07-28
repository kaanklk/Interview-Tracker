insert into users (created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo)
values (now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1'),
       (now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2'),
	   (now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3'),
	   (now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4'),
	   (now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5'),
	   (now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6'),
	   (now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7'),
	   (now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8'),
	   (now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9'),
	   (now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10');

insert into roles (role_name)
values ('Adminstrator'),
       ('Project Manager'),
       ('Recruiter'),
	   ('Sourcer'),
	   ('Interviewer');

insert into users_roles (user_id, role_id)
values (2,4),
       (1,3),
       (2,5),
	   (2,1),
	   (4,4);