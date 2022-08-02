INSERT INTO person (date_of_birth, email, fname, lname, mname, phone, profile_picture_url)
    VALUES
        ('1999-01-01', 'helloman@gmail.com', 'John', 'Doe', '', '+36912345678', 'resources/pictures/profil/pp_001.jpg'),
        ('1980-11-04', 'justaname@gmail.com', 'David', 'Keresztes', '', '+3698765432', 'resources/pictures/profil/pp_002.jpg'),
        ('1993-08-15', 'marta.1993@gmail.com', 'Marta', 'Szabo', '', '+3656231489', 'resources/pictures/profil/pp_003.jpg'),
        ('1982-07-02', 'janos.kovacs@gmail.com', 'Janos', 'Kovacs', '', '+36852741933', 'resources/pictures/profil/pp_004.jpg'),
        ('1975-04-03', 'peter.kovacs@gmail.com', 'Peter', 'Kovacs', '', '+36953214552', 'resources/pictures/profil/pp_005.jpg');

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

        INSERT INTO technical_documentation VALUES (
1, '2000-12-12', 1, 2, 120, FALSE, 'This is the end of you', 3, 'Tech skill comment num1',
	'Tech skill comment num2',
	'Tech skill comment num3',
	'Tech skill comment num4',
	1,2,3,4,
	8,
	'I think he does not understand the way of Ukulele',
	'He understood the role',
	1, 4
)
INSERT INTO technical_documentation VALUES (
2, '2010-10-10', 1, 4, 170, TRUE, 'This is not the end of you', 5, 'High leveled',
	'High',
	'High',
	'VERY High',
	2,1,5,1,
	10,
	'I think he does understand the way of Ukulele!',
	'He understood the role',
	2, 1
)
INSERT INTO technical_documentation VALUES (
3, '2003-03-03', 2, 3, 200, FALSE, 'This is the biggest mistake', 3, 'Low leveled',
	'He can turn on the computer',
	'Wonderful',
	'VERY High',
	1,4,3,5,
	2,
	'Actually he can play the guitar',
	'He does not understand the role, or anything else',
	3, 3
)

INSERT INTO technical_documentation VALUES (
4, '2004-04-04', 3, 2, 111, TRUE, 'This is gonna be a good idea', 5, 'Knows everything',
	'He can turn on the computer',
	'He can turn off the computer',
	'Actually (s)he is a girl',
	5,3,4,5,
	7,
	'Actually he can play the piano',
	'I think he should be the boss because he knows everything about the role',
	5, 5
)

INSERT INTO technical_documentation VALUES (
5, '2022-11-10', 2, 1, 95, FALSE, 'Mister Architect', 4, 'MAXIMUM level',
	'He can do everything',
	'He can do programming in C',
	'He does know even C++',
	4,4,4,5,
	9,
	'Actually he can play the guitar',
	'He does know everything, he is John Snow',
	5, 2
)
