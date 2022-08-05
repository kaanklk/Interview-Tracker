-- DB init script --

INSERT INTO person(id, email, uuid, fname, lname, mname, phone, date_of_birth, profile_picture_url)
    VALUES
        (101, 'helloman@gmail.com', '07f16e6c-10a4-4fbf-9330-8404125b046f', 'John', 'Doe', 'd', '+36912345678', '1993-05-12', 'resources/pictures/profil/pp_001.jpg'),
        (102, 'justaname@gmail.com', '9bacd258-0995-4295-8c79-8fe3de6da6ab', 'David', 'Keresztes', 'd', '+3698765432', '1995-05-12', 'resources/pictures/profil/pp_002.jpg'),
        (103, 'marta.1993@gmail.com', 'b2b36375-354c-462a-a3c0-4b71bcf6e127', 'Marta', 'Sabo', 'd', '+3656231489', '1985-05-02', 'resources/pictures/profil/pp_003.jpg'),
        (104, 'janos.kovacs@gmail.com', '0b3c5a27-7922-416d-baab-46c73ec0b7b0', 'Janos', 'Kovacs', 'd', '+36852741933', '1948-05-10', 'resources/pictures/profil/pp_004.jpg'),
        (105, 'peter.kovacs@gmail.com', '5b6613f0-9faa-488e-95c2-418af8e6f438', 'Peter', 'Kovacs', 'd', '+36953214552', '1989-05-30', 'resources/pictures/profil/pp_005.jpg');

INSERT INTO users (id, created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo)
VALUES (101, now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1'),
       (102, now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2'),
	   (103, now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3'),
	   (104, now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4'),
	   (105, now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5'),
	   (106, now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6'),
	   (107, now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7'),
	   (108, now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8'),
	   (109, now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9'),
	   (110, now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10');

       
INSERT INTO project(id, name, description)
    VALUES
        (101, 'alpha', 'This is a project to build a new developer team'),
        (102, 'beta', 'This is a project to build a new hr team'),
        (103, 'gamma', 'This is a project to build a new data engineer team'),
        (104, 'epsilon', 'This is a project to build a new frontend team'),
        (105, 'omega', 'This is a project to build a new backend team');

INSERT INTO position(id, position_name, project_id, total_count, hired_count, open)
values
    (101, 'Junior Java Developer', 101, 7, 3, true),
    (102, 'Junior C# Developer', 102, 10, 5, true),
    (103, 'Senior Java Developer', 102, 11, 9, false),
    (104, 'Junior Data Engineer', 101, 5, 3, true),
    (105, 'Medor Python Developer', 102, 4, 3, false),
    (106, 'Junior HR Assistant', 103, 10, 4, true);

INSERT INTO candidate (id, uuid, status, cv_path, person_id, position_id)
    VALUES
        (101, '131f1987-ea30-40b1-bf0b-0791e5063599', 'ACCEPTED', 'resources/cv/cv_000.pdf',  101, 101),
        (102, '24cc06fe-2ee2-4963-ac55-3263f1917a1c', 'REJECTED', 'resources/cv/cv_001.pdf',  101, 102),
        (103, 'f0ea1a0a-2527-4b79-9151-e7373f95f51c', 'ACCEPTED', 'resources/cv/cv_002.pdf', 102, 101),
        (104, '879d3947-2bd8-43bf-a089-f77fe709c25d', 'ACCEPTED', 'resources/cv/cv_003.pdf',  103, 102),
        (105, 'ec75a941-1bdc-4000-ab09-a6a82c5cb4c4', 'REJECTED', 'resources/cv/cv_004.pdf',  104, 102),
        (106, '20bab9a9-6058-450e-b1d6-e886dc08d53b', 'ACCEPTED', 'resources/cv/cv_005.pdf', 105, 102);

INSERT INTO language (id, language, LEVEL, candidate_id)
    VALUES 
	    (101, 'english', 'beginner', 101),
        (102, 'english', 'beginner', 102),
        (103, 'english', 'beginner', 103),
        (104, 'hungarian', 'beginner', 104),
        (105, 'hungarian', 'beginner', 105);

INSERT INTO work_experience (id, start_date, end_date, institution, name, summary, candidate_id)
    VALUES
        (101, '2021-04-01', '2021-10-10', 'TCS', 'back-end developer', 'Worked on REST API.', 101),
        (102, '2021-04-01', '2021-03-20', 'MS', 'back-end developer', 'Worked on REST API.', 101),
        (103, '2020-04-11', '2021-12-10', 'TCS', 'data-engineer developer', 'Worked on db.', 102),
        (104, '2019-01-21', '2020-02-05', 'OTP', 'data-engineer developer', 'Worked on db.', 102),
        (105, '2008-06-02', '2019-01-10', 'TCS', 'front-end developer', 'Worked with Angular.', 103),
        (106, '2009-08-01', '2020-04-10', 'TCS', 'front-end developer', 'Worked with Angular.', 104),
        (107, '2015-02-12', '2021-10-11', 'TCS', 'front-end developer', 'Worked with Angular.', 105);

INSERT INTO education (id, start_date, end_date, information, institution, candidate_id)
    VALUES
        (101, '2019-09-01', '2023-01-01', 'BSc', 'BME', 101),
        (102, '2019-09-01', '2023-01-02', 'BSc', 'ELTE', 102),
        (103, '2010-09-01', '2013-01-02', 'BSc', 'BME', 103),
        (104, '2008-09-01', '2011-01-04', 'MSc', 'OLTE', 104),
        (105, '2019-10-01', '2020-01-11', 'OKJ', 'ELTE', 105);

INSERT INTO roles (id, role_name, uuid)
VALUES (101, 'Management Interviewer', 'aa8b7a22-4da4-41d9-8e91-023303383f4d'),
       (102, 'Project Manager', 'aa8b7a22-4da4-41d9-8e91-023303383f4d'),
       (103, 'Recruiter', '10b526ae-f78c-4615-8cb1-e7f21239a4fe'),
	   (104, 'Sourcer', 'e5b73f8d-d858-468c-9934-4cbe2f083563'),
	   (105, 'Technical Interviewer', 'ba9766e1-e3b3-4229-8639-53db4676294d');

INSERT INTO user_roles (id, user_id, project_id)
	VALUES 	
        (101, 101, 101),
        (102, 102, 102),
        (103, 103, 103),
        (104, 104, 104),
        (105, 105, 105);

INSERT INTO user_roles_role (user_roles_id, role_id)
VALUES
		(101, 102),
       	(101, 103),
       	(102, 105),
	   	(103, 101),
	   	(104, 104);

