
INSERT INTO person (date_of_birth, email, fname, lname, mname, phone, profile_picture_url)
    VALUES
        (DATE(1999-01-01), 'helloman@gmail.com', 'John', 'Doe', '', '+36912345678', 'resources/pictures/profil/pp_001.jpg'),
        (DATE(1980-11-04), 'justaname@gmail.com', 'David', 'Keresztes', '', '+3698765432', 'resources/pictures/profil/pp_002.jpg'),
        (DATE(1993-08-15), 'marta.1993@gmail.com', 'Marta', 'Szabo', '', '+3656231489', 'resources/pictures/profil/pp_003.jpg'),
        (DATE(1982-07-02), 'janos.kovacs@gmail.com', 'Janos', 'Kovacs', '', '+36852741933', 'resources/pictures/profil/pp_004.jpg'),
        (DATE(1975-04-03), 'peter.kovacs@gmail.com', 'Peter', 'Kovacs', '', '+36953214552', 'resources/pictures/profil/pp_005.jpg');

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
        (DATE(2021-04-01), DATE(2021-10-10), 'TCS', 'back-end developer', 'Worked on REST API.', 1),
        (DATE(2021-04-01), DATE(2021-03-20), 'MS', 'back-end developer', 'Worked on REST API.', 1),
        (DATE(2020-04-11), DATE(2021-12-10), 'TCS', 'data-engineer developer', 'Worked on db.', 2),
        (DATE(2019-01-21), DATE(2020-02-05), 'OTP', 'data-engineer developer', 'Worked on db.', 2),
        (DATE(2008-06-02), DATE(2019-01-10), 'TCS', 'front-end developer', 'Worked with Angular.', 3),
        (DATE(2009-08-01), DATE(2020-04-10), 'TCS', 'front-end developer', 'Worked with Angular.', 4),
        (DATE(2015-02-12), DATE(2021-10-11), 'TCS', 'front-end developer', 'Worked with Angular.', 5);

INSERT INTO education (start_date, end_date, information, institution, candidate_id)
    VALUES
        (DATE(2019-09-01), DATE(2023-01-01), 'BSc', 'BME', 1),
        (DATE(2019-09-01), DATE(2023-01-02), 'BSc', 'ELTE', 2),
        (DATE(2010-09-01), DATE(2013-01-02), 'BSc', 'BME', 3),
        (DATE(2008-09-01), DATE(2011-01-04), 'MSc', 'OLTE', 4),
        (DATE(2019-10-01), DATE(2020-01-11), 'OKJ', 'ELTE', 5);

