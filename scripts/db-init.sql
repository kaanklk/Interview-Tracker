-- DB init script --


INSERT INTO person(email, uuid, fname, lname, mname, phone, date_of_birth, profile_picture_url)
    VALUES
        ('helloman@gmail.com', '07f16e6c-10a4-4fbf-9330-8404125b046f', 'John', 'Doe', 'd', '+36912345678', '1993-05-12', 'resources/pictures/profil/pp_001.jpg'),
        ('justaname@gmail.com', '9bacd258-0995-4295-8c79-8fe3de6da6ab', 'David', 'Keresztes', 'd', '+3698765432', '1995-05-12', 'resources/pictures/profil/pp_002.jpg'),
        ('marta.1993@gmail.com', 'b2b36375-354c-462a-a3c0-4b71bcf6e127', 'Marta', 'Sabo', 'd', '+3656231489', '1985-05-02', 'resources/pictures/profil/pp_003.jpg'),
        ('janos.kovacs@gmail.com', '0b3c5a27-7922-416d-baab-46c73ec0b7b0', 'Janos', 'Kovacs', 'd', '+36852741933', '1948-05-10', 'resources/pictures/profil/pp_004.jpg'),
        ('peter.kovacs@gmail.com', '5b6613f0-9faa-488e-95c2-418af8e6f438', 'Peter', 'Kovacs', 'd', '+36953214552', '1989-05-30', 'resources/pictures/profil/pp_005.jpg');

INSERT INTO users (created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo)
VALUES (now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1'),
       (now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2'),
	   (now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3'),
	   (now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4'),
	   (now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5'),
	   (now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6'),
	   (now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7'),
	   (now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8'),
	   (now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9'),
	   (now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10');

       
INSERT INTO project(name, project_manager_id, description, recruiter_id, sourcer_id, deadline)
    VALUES
        ('alpha', 1, 'This is a project to build a new developer team', 4, 1, '2022-06-20'),
        ('beta', 2, 'This is a project to build a new hr team', 5, 4, '2022-01-23'),
        ('gamma', 3, 'This is a project to build a new data engineer team', 2, 3, '2021-03-03'),
        ('epsilon', 4, 'This is a project to build a new frontend team', 2, 3, '2022-02-02'),
        ('omega', 5, 'This is a project to build a new backend team', 4, 5, '2019-07-03');

INSERT INTO position(position_name, project_id, total_count, hired_count, open)
values
    ('Junior Java Developer', 1, 7, 3, true),
    ('Junior C# Developer', 2, 10, 5, true),
    ('Senior Java Developer', 2, 11, 9, false),
    ('Junior Data Engineer', 1, 5, 3, true),
    ('Medor Python Developer', 2, 4, 3, false),
    ('Junior HR Assistant', 3, 10, 4, true);

INSERT INTO candidate (uuid, status, cv_path, person_id, position_id)
    VALUES
        ('131f1987-ea30-40b1-bf0b-0791e5063599', 'ACCEPTED', 'resources/cv/cv_000.pdf',  1, 1),
        ('24cc06fe-2ee2-4963-ac55-3263f1917a1c', 'REJECTED', 'resources/cv/cv_001.pdf',  1, 2),
        ('f0ea1a0a-2527-4b79-9151-e7373f95f51c', 'ACCEPTED', 'resources/cv/cv_002.pdf', 2, 1),
        ('879d3947-2bd8-43bf-a089-f77fe709c25d', 'ACCEPTED', 'resources/cv/cv_003.pdf',  3, 2),
        ('ec75a941-1bdc-4000-ab09-a6a82c5cb4c4', 'REJECTED', 'resources/cv/cv_004.pdf',  4, 2),
        ('20bab9a9-6058-450e-b1d6-e886dc08d53b', 'ACCEPTED', 'resources/cv/cv_005.pdf', 5, 2);

INSERT INTO language (language, LEVEL, candidate_id)
    VALUES 
	    ('english', 'beginner', 1),
        ('english', 'beginner', 2),
        ('english', 'beginner', 3),
        ('hungarian', 'beginner', 4),
        ('hungarian', 'beginner', 5);

INSERT INTO work_experience (start_date, end_date, institution, name, summary, candidate_id)
    VALUES
        ('2021-04-01', '2021-10-10', 'TCS', 'back-end developer', 'Worked on REST API.', 1),
        ('2021-04-01', '2021-03-20', 'MS', 'back-end developer', 'Worked on REST API.', 1),
        ('2020-04-11', '2021-12-10', 'TCS', 'data-engineer developer', 'Worked on db.', 2),
        ('2019-01-21', '2020-02-05', 'OTP', 'data-engineer developer', 'Worked on db.', 2),
        ('2008-06-02', '2019-01-10', 'TCS', 'front-end developer', 'Worked with Angular.', 3),
        ('2009-08-01', '2020-04-10', 'TCS', 'front-end developer', 'Worked with Angular.', 4),
        ('2015-02-12', '2021-10-11', 'TCS', 'front-end developer', 'Worked with Angular.', 5);

INSERT INTO education (start_date, end_date, information, institution, candidate_id)
    VALUES
        ('2019-09-01', '2023-01-01', 'BSc', 'BME', 1),
        ('2019-09-01', '2023-01-02', 'BSc', 'ELTE', 2),
        ('2010-09-01', '2013-01-02', 'BSc', 'BME', 3),
        ('2008-09-01', '2011-01-04', 'MSc', 'OLTE', 4),
        ('2019-10-01', '2020-01-11', 'OKJ', 'ELTE', 5);

INSERT INTO roles (role_name, uuid)
VALUES ('Management Interviewer', 'aa8b7a22-4da4-41d9-8e91-023303383f4d'),
       ('Project Manager', 'aa8b7a22-4da4-41d9-8e91-023303383f4d'),
       ('Recruiter', '10b526ae-f78c-4615-8cb1-e7f21239a4fe'),
	   ('Sourcer', 'e5b73f8d-d858-468c-9934-4cbe2f083563'),
	   ('Technical Interviewer', 'ba9766e1-e3b3-4229-8639-53db4676294d');

INSERT INTO user_roles (user_id, project_id)
	VALUES 	
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
        (5, 5);

INSERT INTO users_roles (user_id, roles_role_id)
	VALUES 	
			(1, 1),
			(1, 2),
			(2, 3),
			(3, 4),
			(4, 5);
			

INSERT INTO user_roles_role (user_roles_id, role_id)
VALUES
		(1,2),
       	(1,3),
       	(2,5),
	   	(3,1),
	   	(4,4);

