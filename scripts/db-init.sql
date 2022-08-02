-- DB init script --


INSERT INTO person(id, date_of_birth, email, fname, lname, mname, phone, profile_picture_url)
    VALUES
        (1, '1999-01-01', 'helloman@gmail.com', 'John', 'Doe', 'd', '+36912345678', 'resources/pictures/profil/pp_001.jpg'),
        (2, '1980-11-04', 'justaname@gmail.com', 'David', 'Keresztes', 'd', '+3698765432', 'resources/pictures/profil/pp_002.jpg'),
        (3, '1993-08-15', 'marta.1993@gmail.com', 'Marta', 'Szabo', 'd', '+3656231489', 'resources/pictures/profil/pp_003.jpg'),
        (4, '1982-07-02', 'janos.kovacs@gmail.com', 'Janos', 'Kovacs', 'd', '+36852741933', 'resources/pictures/profil/pp_004.jpg'),
        (5, '1975-04-03', 'peter.kovacs@gmail.com', 'Peter', 'Kovacs', 'd', '+36953214552', 'resources/pictures/profil/pp_005.jpg');

INSERT INTO users (id, created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo)
VALUES (1, now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1'),
       (2, now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2'),
	   (3, now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3'),
	   (4, now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4'),
	   (5, now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5'),
	   (6, now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6'),
	   (7, now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7'),
	   (8, now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8'),
	   (9, now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9'),
	   (10, now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10');

       
INSERT INTO project(id, name, project_manager_id, description, recruiter_id, sourcer_id, deadline)
    VALUES
        (1, 'alpha', 1, 'This is a project to build a new developer team', 4, 1, '2022-06-20'),
        (2, 'beta', 2, 'This is a project to build a new hr team', 5, 4, '2022-01-23'),
        (3, 'gamma', 3, 'This is a project to build a new data engineer team', 2, 3, '2021-03-03'),
        (4, 'epsilon', 4, 'This is a project to build a new frontend team', 2, 3, '2022-02-02'),
        (5, 'omega', 5, 'This is a project to build a new backend team', 4, 5, '2019-07-03');

INSERT INTO position(id, position_name, project_id, total_count, hired_count, open)
values
    (1, 'Junior Java Developer', 1, 7, 3, true),
    (2, 'Junior C# Developer', 2, 10, 5, true),
    (3, 'Senior Java Developer', 2, 11, 9, false),
    (4, 'Junior Data Engineer', 1, 5, 3, true),
    (5, 'Medor Python Developer', 2, 4, 3, false),
    (6, 'Junior HR Assistant', 3, 10, 4, true);

INSERT INTO candidate (id, cv_path, status, person_id, position_id)
    VALUES
        (1, 'resources/cv/cv_000.pdf', 'accepted', 1, 1),
        (2, 'resources/cv/cv_001.pdf', 'rejected', 1, 2),
        (3, 'resources/cv/cv_002.pdf', 'accepted', 2, 1),
        (4, 'resources/cv/cv_003.pdf', 'accepted', 3, 2),
        (5, 'resources/cv/cv_004.pdf', 'accepted', 4, 2),
        (6, 'resources/cv/cv_005.pdf', 'accepted', 5, 2);

INSERT INTO language (id, language, LEVEL, candidate_id)
    VALUES 
	    (1, 'english', 'beginner', 1),
        (2, 'english', 'beginner', 2),
        (3, 'english', 'beginner', 3),
        (4, 'hungarian', 'beginner', 4),
        (5, 'hungarian', 'beginner', 5);

INSERT INTO work_experience (id, start_date, end_date, institution, name, summary, candidate_id)
    VALUES
        (1, '2021-04-01', '2021-10-10', 'TCS', 'back-end developer', 'Worked on REST API.', 1),
        (2, '2021-04-01', '2021-03-20', 'MS', 'back-end developer', 'Worked on REST API.', 1),
        (3, '2020-04-11', '2021-12-10', 'TCS', 'data-engineer developer', 'Worked on db.', 2),
        (4, '2019-01-21', '2020-02-05', 'OTP', 'data-engineer developer', 'Worked on db.', 2),
        (5, '2008-06-02', '2019-01-10', 'TCS', 'front-end developer', 'Worked with Angular.', 3),
        (6, '2009-08-01', '2020-04-10', 'TCS', 'front-end developer', 'Worked with Angular.', 4),
        (7, '2015-02-12', '2021-10-11', 'TCS', 'front-end developer', 'Worked with Angular.', 5);

INSERT INTO education (id, start_date, end_date, information, institution, candidate_id)
    VALUES
        (1, '2019-09-01', '2023-01-01', 'BSc', 'BME', 1),
        (2, '2019-09-01', '2023-01-02', 'BSc', 'ELTE', 2),
        (3, '2010-09-01', '2013-01-02', 'BSc', 'BME', 3),
        (4, '2008-09-01', '2011-01-04', 'MSc', 'OLTE', 4),
        (5, '2019-10-01', '2020-01-11', 'OKJ', 'ELTE', 5);

INSERT INTO roles (id, role_name, uuid)
VALUES (1, 'Management Interviewer', 'aa8b7a22-4da4-41d9-8e91-023303383f4d'),
       (2, 'Project Manager', 'aa8b7a22-4da4-41d9-8e91-023303383f4d'),
       (3, 'Recruiter', '10b526ae-f78c-4615-8cb1-e7f21239a4fe'),
	   (4, 'Sourcer', 'e5b73f8d-d858-468c-9934-4cbe2f083563'),
	   (5, 'Technical Interviewer', 'ba9766e1-e3b3-4229-8639-53db4676294d');

INSERT INTO user_roles (id, project_id, user_id, uuid)
	VALUES 	
			(1, 'd5692d0f-665d-4547-a494-5b6c3011447c', 'd0be0ead-1392-4dc5-a67b-6325d873b255', '8d706a76-e252-4f9c-af2d-c20a3c4d6e2e'),
			(2, '50bb0218-2537-4fcb-b8c8-83449981b59c', '07bfc7f2-89ad-43d2-af00-61667754c1ce', 'd2866a36-6e15-471a-9b32-163593c4052e'),
			(3, '0f2b581d-3d08-4d8d-8df0-11944ffa3a54', 'a7a03f20-5214-4d78-8cf5-02c2339b7f7e', 'd8d93f23-bcbb-4d44-9254-49b00253b6ee'),
			(4, 'be1a8d14-b108-448f-97e3-508566a0b1a0', 'd246a0a7-ce00-48a2-8611-916dd073252c', 'afea27a2-99e1-48d8-9b84-b97876059ad2'),
			(5, '1bf7c7bb-f4b6-4b9c-8156-56ebf6bdf777', '43174beb-6955-400f-b697-b7df577a6fc1', '6d521dbb-c0bd-492b-a6ff-b3d1f24d28a2');

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

