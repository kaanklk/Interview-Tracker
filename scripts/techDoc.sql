
insert into users (id, admin, created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo, uuid)
values (1, true, now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1', 'dfb79789-98ff-4709-b2a2-f9aad760f1e1'),
       (2, true, now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1'),
	   (3, false, now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2'),
	   (4, true, now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3'),
	   (5, true, now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4'),
	   (6, false, now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5'),
	   (7, true, now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e6'),
	   (8, false, now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e7'),
	   (9, true, now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e8'),
	   (10, false, now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10', 'dfb79789-98ff-4709-b2a2-f9a6da60f1e8');



INSERT INTO project (id, name, project_manager, description, recruiter, sourcer, deadline, uuid)
    VALUES
        (1, 'alpha', 1, 'This is a project to build a new developer team', 4, 6, 2022-06-20, '79a7f7a0-1196-11ed-861d-0242ac120002'),
        (2, 'beta', 2, 'This is a project to build a new hr team', 9, 8, 2022-01-23, '80903b22-1196-11ed-861d-0242ac120002'),
        (3, 'gamma', 3, 'This is a project to build a new data engineer team', 2, 2, 2022-01-23, 'b2eeb4e0-1196-11ed-861d-0242ac120002'),
        (4, 'epsilon', 4, 'This is a project to build a new frontend team', 2, 2, 2022-01-23,'b7c1b364-1196-11ed-861d-0242ac120002'),
        (5, 'omega', 5, 'This is a project to build a new backend team', 2, 7, 2022-01-23, 'bb65fade-1196-11ed-861d-0242ac120002');

insert into position(id,position_name, project_id, total_count, hired_count, open, uuid)
values
    ( 1,'Junior Java Developer', 1, 7, 3, true, 'b9dc902a-11a9-11ed-861d-0242ac120002'),
    (2,'Junior C# Developer', 2, 10, 5, true, '6d19e030-118e-11ed-861d-0242ac120002'),
    (3,'Senior Java Developer', 3, 11, 9, false, '6f19e030118e-11ed-861d-0242ac120002'),
    (4,'Junior Data Engineer', 4, 5, 3, true, '6f19e030-118e-11ed-861d-0242aa120002'),
    (5, 'Medor Python Developer', 5, 4, 3, false, '6f19e030-a18e-11ed-861d-0242ac120002'),
    (6, 'Junior HR Assistant', 3, 10, 3, true, '6f19e030-118e-1aed-861d-0242ac120002');


INSERT INTO person (id,date_of_birth, email, fname, lname, mname, phone, profile_picture_url)
    VALUES
        (1,'1999-01-01', 'helloman@gmail.com', 'John', 'Doe', '', '+36912345678', 'resources/pictures/profil/pp_001.jpg'),
        (2,'1980-11-04', 'justaname@gmail.com', 'David', 'Keresztes', '', '+3698765432', 'resources/pictures/profil/pp_002.jpg'),
        (3,'1993-08-15', 'marta.1993@gmail.com', 'Marta', 'Szabo', '', '+3656231489', 'resources/pictures/profil/pp_003.jpg'),
        (4,'1982-07-02', 'janos.kovacs@gmail.com', 'Janos', 'Kovacs', '', '+36852741933', 'resources/pictures/profil/pp_004.jpg'),
        (5,'1975-04-03', 'peter.kovacs@gmail.com', 'Peter', 'Kovacs', '', '+36953214552', 'resources/pictures/profil/pp_005.jpg');
INSERT INTO candidate (id,cv_path, status, person_id, position_id,uuid)
    VALUES
        (1,'resources/cv/cv_000.pdf', 'accepted', 1, 1,'801a188c-11ed-11ed-861d-0242ac120002'),
        (2,'resources/cv/cv_001.pdf', 'rejected', 1, 2,'8f428c22-11ed-11ed-861d-0242ac120002'),
        (3,'resources/cv/cv_002.pdf', 'accepted', 2, 1,'962d2b46-11ed-11ed-861d-0242ac120002'),
        (4,'resources/cv/cv_003.pdf', 'accepted', 3, 2,'c0be68e4-e86d-4389-a8af-65277b27345e'),
        (5,'resources/cv/cv_004.pdf', 'accepted', 4, 2,'b7f68596-6777-49d1-aeb7-8c1a522a8bf3'),
        (6,'resources/cv/cv_005.pdf', 'accepted', 5, 2,'c8760776-d0aa-4040-88a3-4f2e13a39145');
select * from candidate;

INSERT INTO technical_documentation VALUES (
1, '2000-12-12', 1, 2, 120, FALSE, 'This is the end of you', 3, 'Tech skill comment num1',
	'Tech skill comment num2',
	'Tech skill comment num3',
	'Tech skill comment num4',
	1,2,3,4,
	8,
	'I think he does not understand the way of Ukulele',
	'He understood the role',
  	'ea51f356-118d-11ed-861d-0242ac120002',
	1, 4,1
);

INSERT INTO technical_documentation VALUES (
2, '2000-12-12', 1, 2, 120, FALSE, 'This is the end of you', 3, 'Tech skill comment num1',
	'Tech skill comment num2',
	'Tech skill comment num3',
	'Tech skill comment num4',
	4,2,3,1,
	4,
	'I think he does understand the way of Ukulele',
	'He understood the role',
  	'ea51f656-138c-11ed-861d-0242ac120002',
	2, 4,3
);
select * from technical_documentation;

