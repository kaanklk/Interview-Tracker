-- DB init script --

INSERT INTO person(id, email, uuid, fname, lname, mname, phone, date_of_birth, profile_picture_url)
    VALUES
        (101, 'helloman@gmail.com', '07f16e6c-10a4-4fbf-9330-8404125b046f', 'John', 'Doe', 'd', '+36912345678', '1993-05-12', 'resources/pictures/profil/pp_001.jpg'),
        (102, 'justaname@gmail.com', '9bacd258-0995-4295-8c79-8fe3de6da6ab', 'David', 'Keresztes', 'd', '+3698765432', '1995-05-12', 'resources/pictures/profil/pp_002.jpg'),
        (103, 'marta.1993@gmail.com', 'b2b36375-354c-462a-a3c0-4b71bcf6e127', 'Marta', 'Sabo', 'd', '+3656231489', '1985-05-02', 'resources/pictures/profil/pp_003.jpg'),
        (104, 'janos.kovacs@gmail.com', '0b3c5a27-7922-416d-baab-46c73ec0b7b0', 'Janos', 'Kovacs', 'd', '+36852741933', '1948-05-10', 'resources/pictures/profil/pp_004.jpg'),
        (105, 'peter.kovacs@gmail.com', '5b6613f0-9faa-488e-95c2-418af8e6f438', 'Peter', 'Kovacs', 'd', '+36953214552', '1989-05-30', 'resources/pictures/profil/pp_005.jpg');

INSERT INTO users (id, uuid, created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo, admin)
VALUES (101, 'fc0e88ec-cb24-42e2-9e55-f85512c584eb', now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1', true),
       (102, '11b6cab6-4a8f-40d0-be64-50a7b338523c', now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2', false),
	   (103, '5d7e1836-36f2-4cf1-bacc-26006bf25153', now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3', true),
	   (104, '4cba21f6-13bd-49db-b1a5-3eb0bb5d9f1c', now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4', true),
	   (105, '6f06dead-fa00-4f66-9a35-8813437a8906', now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5', false),
	   (106, '62f393bd-7b05-4479-8e06-034a3a125581', now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6', true),
	   (107, '1ba75c20-676e-42bf-ac9b-a5f22833a470', now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7', false),
	   (108, '1a5e6859-7a7b-495f-b064-5c66a7a85510', now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8', true),
	   (109, '44097d7e-d916-4276-bbae-47385f92b236', now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9', false),
	   (110, '3c9dead7-638f-4745-9545-615f269f65d6', now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John0', 'Doe0', 'Diego0', '+3687238323', 'url10', false);

INSERT INTO project(id, uuid, name, description)
    VALUES
        (101, 'ede22832-77e9-4374-8b3b-142e80829d74', 'alpha', 'This is a project to build a new developer team'),
        (102, '34f10181-465b-4573-87b1-12e5bd70d58a', 'beta', 'This is a project to build a new hr team'),
        (103, '29562c07-b0ca-4af0-8d74-531857aa6fb3', 'gamma', 'This is a project to build a new data engineer team'),
        (104, '0e288a1e-b9a4-42cd-909a-812929b0e7a9', 'epsilon', 'This is a project to build a new frontend team'),
        (105, 'd894a591-5b2f-4bcb-8353-4fcca82a6ed5', 'omega', 'This is a project to build a new backend team');

INSERT INTO position(id, uuid, position_name, project_id, total_count, hired_count, open)
values
    (101, '3ff269d3-e1e8-417a-a2f8-2a629eef5d9b', 'Junior Java Developer', 101, 7, 3, true),
    (102, 'b309fa51-cb5f-4e7a-9eaf-f6dbd2e9ee10', 'Junior C# Developer', 102, 10, 5, true),
    (103, '48ed010a-5eee-4c02-96aa-bae833cc2447', 'Senior Java Developer', 102, 11, 9, false),
    (104, 'b99ceb50-31f6-4a90-a5a2-f708ea4eac4c', 'Junior Data Engineer', 101, 5, 3, true),
    (105, 'e05fd854-4685-402c-a270-1a4eb1ed5099', 'Medor Python Developer', 102, 4, 3, false),
    (106, '8721be71-a153-4944-8ce8-da09ca22922f', 'Junior HR Assistant', 103, 10, 4, true);

INSERT INTO candidate (id, uuid, status, cv_path, person_id, position_id, project_id)
    VALUES
        (101, '131f1987-ea30-40b1-bf0b-0791e5063599', 'OFFER_ACCEPTED', 'resources/cv/cv_000.pdf',  101, 101, 101),
        (102, '24cc06fe-2ee2-4963-ac55-3263f1917a1c', 'REJECTED', 'resources/cv/cv_001.pdf',  101, 102, 101),
        (103, 'f0ea1a0a-2527-4b79-9151-e7373f95f51c', 'OFFER_SENT', 'resources/cv/cv_002.pdf', 102, 101, 101),
        (104, '879d3947-2bd8-43bf-a089-f77fe709c25d', 'OFFER_ACCEPTED', 'resources/cv/cv_003.pdf',  103, 102, 101),
        (105, 'ec75a941-1bdc-4000-ab09-a6a82c5cb4c4', 'REJECTED', 'resources/cv/cv_004.pdf',  104, 102, 101),
        (106, '20bab9a9-6058-450e-b1d6-e886dc08d53b', 'OFFER_ACCEPTED', 'resources/cv/cv_005.pdf', 105, 102, 101);

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
VALUES (101, 'Management Interviewer', '07f16e6c-10a4-4fbf-9330-8404125b046f'),
       (102, 'project_manager', 'aa8b7a22-4da4-41d9-8e91-023303383f4d'),
       (103, 'recruiter', '10b526ae-f78c-4615-8cb1-e7f21239a4fe'),
       (104, 'sourcer', 'e5b73f8d-d858-468c-9934-4cbe2f083563'),
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

INSERT INTO technical_documentation (id, uuid, candidate_id, interviewer_one, interviewer_two)
	VALUES
		(101, 'a470cde2-a6e0-49aa-9db1-bfb4f61a2319', 101, 102, 103),
		(102, 'f0006722-91f6-4702-a8a1-3f66176f0da4', 102, 101, 102),
		(103, 'c43dfece-596a-47c1-860b-06e98383bf04', 103, 103, 104),
		(104, '7747e757-8cf9-4709-b7f4-2e41c80e4dda', 104, 101, 103),
		(105, 'ef4a38dc-f83f-45f2-b80a-bd9a3868ee30', 105, 102, 101);

INSERT INTO management_documentation (id, uuid, candidate_id, project_id, interviewer_id1 , interviewer_id2)
	VALUES
		(101, '9d158f42-615f-4657-a99d-902820c8adab', 101, 101, 102, 103),
		(102, '0e0ccfe0-a25f-42e2-801d-c53a082408d8', 102, 101, 101, 102),
		(103, '3ca1e412-760f-4276-9805-abdb5900db6c', 103, 101, 103, 104),
		(104, 'cb5057b3-9b3c-4523-85cb-9eca3771a111', 104, 101, 101, 103),
		(105, '6290e09d-c5d9-4018-9137-1511ccb11fd3', 105, 101, 102, 101);
	
INSERT INTO timeslot (id, uuid, start_time, end_time, "type", is_completed)
	VALUES
		(101, 'edc1a219-55ad-4edc-84c0-844409bf70fb', '2022-08-04 13:30:00', '2022-08-04 14:00:00', '', true),
		(102, '1916d95c-dfd5-444d-85f3-6b16d785f826', '2022-08-05 13:30:00', '2022-08-05 14:00:00', '', false),
		(103, '0d4c8f66-6f4e-477c-bf5f-e1c6736e657b', '2022-08-05 13:30:00', '2022-08-05 14:00:00', '', false),
		(104, 'e6cb3e1d-5ecd-4f77-badd-e7ff0b77e591', '2022-08-06 13:30:00', '2022-08-06 14:00:00', '', false),
		(105, 'e70b8802-d4bb-4231-b0d5-9ecf081c0dae', '2022-08-06 13:30:00', '2022-08-06 14:00:00', '', false),
		(106, '22480204-3fda-463c-bc4e-13cfc13b8055', '2022-08-07 13:30:00', '2022-08-07 14:00:00', '', true);
	
INSERT INTO interview_type (id, type_name)
	VALUES
		(101, 'technical'),
		(102, 'management');

INSERT INTO interview (id, uuid, candidate_id, project_id, timeslot_id, type_id, interviewer_one_id, interviewer_two_id, is_completed)
	VALUES
		(101, '326d2506-f2e7-4bdb-bb43-dd03c4019b51', 101, 101, 101, 101, 101, 102, true),
		(102, '1da1bdfd-9213-4ef7-a364-3586a5d33612', 102, 101, 102, 102, 102, 103, false),
		(103, 'cacb8811-7095-4b77-8c42-91720b800fa6', 103, 101, 103, 101, 103, 104, false),
		(104, '28a62838-6f82-4387-8f54-59e4631a4e4d', 104, 102, 104, 102, 104, 105, false),
		(105, '990857bf-20ae-44dd-97be-43690a2f1a3b', 105, 103, 105, 101, 105, 101, false),
		(106, '844b19ee-1816-4857-be94-10fd993bca04', 106, 104, 106, 102, 101, 102, true);
		
		
		