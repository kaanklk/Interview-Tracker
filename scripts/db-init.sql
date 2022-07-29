-- DB init script --


INSERT INTO person (date_of_birth, email, fname, lname, mname, phone, profile_picture_url)
    VALUES
        ('1999-01-01', 'helloman@gmail.com', 'John', 'Doe', '', '+36912345678', 'resources/pictures/profil/pp_001.jpg'),
        ('1980-11-04', 'justaname@gmail.com', 'David', 'Keresztes', '', '+3698765432', 'resources/pictures/profil/pp_002.jpg'),
        ('1993-08-15', 'marta.1993@gmail.com', 'Marta', 'Szabo', '', '+3656231489', 'resources/pictures/profil/pp_003.jpg'),
        ('1982-07-02', 'janos.kovacs@gmail.com', 'Janos', 'Kovacs', '', '+36852741933', 'resources/pictures/profil/pp_004.jpg'),
        ('1975-04-03', 'peter.kovacs@gmail.com', 'Peter', 'Kovacs', '', '+36953214552', 'resources/pictures/profil/pp_005.jpg');

INSERT INTO project (name, projectManager, description, recruiter, sourcer, deadline)
    VALUES
        ('alpha', 1, 'This is a project to build a new developer team', 4, 6, DATE(2022 - 06 - 20))
        ('beta', 2, 'This is a project to build a new hr team', 9, 8, DATE(2022 - 01 - 23))
        ('gamma', 3, 'This is a project to build a new data engineer team', 11, 33, DATE(2021 - 03 - 03))
        ('epsilon', 4, 'This is a project to build a new frontend team', 21, 45, DATE(2022 - 02 - 02))
        ('omega', 5, 'This is a project to build a new backend team', 23, 7, DATE(2019 - 07 - 03))

insert into postion(position_name, project_id, total_count, hired_count, open)
vaues(
    ("Junior Java Developer", 1, 7, 3, true),
    ("Junior C# Developer", 2, 10, 5, true),
    ("Senior Java Developer", 2, 11, 9, false),
    ("Junior Data Engineer", 1, 5, 3, true),
    ("Medor Python Developer", 2, 4, 3, false),
    ("Junior HR Assistant", 3, 10, , true)
)

INSERT INTO candidate (cv_path, status, person_id, position_id)
    VALUES
        ('resources/cv/cv_000.pdf', 'accepted', 1, 1),
        ('resources/cv/cv_001.pdf', 'rejected', 1, 2),
        ('resources/cv/cv_002.pdf', 'accepted', 2, 1),
        ('resources/cv/cv_003.pdf', 'accepted', 3, 2),
        ('resources/cv/cv_004.pdf', 'accepted', 4, 2),
        ('resources/cv/cv_005.pdf', 'accepted', 5, 2);

INSERT INTO language (language, level, candidate_id)
    VALUES
        ('english', 'beginner', 1),
        ('german', 'advanced', 1),
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
