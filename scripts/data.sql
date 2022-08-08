SET session_replication_role = 'replica';

insert into position(id, position_name, project_id, total_count, hired_count, open, uuid)
values
    (10, 'Junior Java Developer', 1, 7, 3, true, 'b9dc902a-11a9-11ed-861d-0242ac120002'),
    (12,'Junior C# Developer', 2, 10, 5, true, '3e157c50-1306-11ed-861d-0242ac120002'),
    (13,'Senior Java Developer', 3, 11, 9, false, '438a7cd0-1306-11ed-861d-0242ac120002'),
    (14,'Junior Data Engineer', 4, 5, 3, true, '46dc6b1e-1306-11ed-861d-0242ac120002'),
    (15, 'Medor Python Developer', 5, 4, 3, false, '4ddc35d4-1306-11ed-861d-0242ac120002'),
    (16, 'Junior HR Assistant', 3, 10, 3, true, '54fafe36-1306-11ed-861d-0242ac120002');

INSERT INTO project (id, name, description, uuid)
    VALUES (1, 'alpha','This is a project to build a new developer team', '79a7f7a0-1196-11ed-861d-0242ac120002'),
    (2, 'beta','This is a project to build a new hr team', '80903b22-1196-11ed-861d-0242ac120002'),
    (3, 'gamma','This is a project to build a new data engineer team', 'b2eeb4e0-1196-11ed-861d-0242ac120002'),
    (4, 'epsilon', 'This is a project to build a new frontend team',  'b7c1b364-1196-11ed-861d-0242ac120002'),
    (5, 'omega','This is a project to build a new backend team',  'bb65fade-1196-11ed-861d-0242ac120002');

INSERT INTO users (id, admin, created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo, uuid)
    VALUES (1, TRUE, now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1', 'dfb79789-98ff-4709-b2a2-f9aad760f1e1'), (2, TRUE, now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1'), (3, FALSE, now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2'), (4, TRUE, now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3'), (5, TRUE, now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4'), (6, FALSE, now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5'), (7, TRUE, now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e6'), (8, FALSE, now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e7'), (9, TRUE, now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e8'), (10, FALSE, now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10', 'dfb79789-98ff-4709-b2a2-f9a6da60f1e8');

INSERT INTO roles (role_name, uuid)
    VALUES ('Management Interviewer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1'), ('Project Manager', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2'), ('Recruiter', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3'), ('Sourcer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4'), ('Technical Interviewer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5');

INSERT INTO user_roles (user_id, project_id)
    VALUES (1, 1), (2, 2), (3, 3), (4, 4), (4, 5);

INSERT INTO user_roles_role (user_roles_id, role_id)
    VALUES (1, 2), (1, 3), (2, 5), (3, 1), (4, 4);
INSERT INTO person (id,date_of_birth, email, fname, lname, mname, phone )
    VALUES
        (1,'1999-01-01', 'helloman@gmail.com', 'John', 'Doe', '', '+36912345678' ),
        (2,'1980-11-04', 'justaname@gmail.com', 'David', 'Keresztes', '', '+3698765432'),
        (3,'1993-08-15', 'marta.1993@gmail.com', 'Marta', 'Szabo', '', '+3656231489'),
        (4,'1982-07-02', 'janos.kovacs@gmail.com', 'Janos', 'Kovacs', '', '+36852741933'),
        (5,'1975-04-03', 'peter.kovacs@gmail.com', 'Peter', 'Kovacs', '', '+36953214552');

INSERT INTO candidate (id,cv_path, status, person_id, position_id,uuid)
    VALUES
        (1,'resources/cv/cv_000.pdf', 0, 1, 10,'801a188c-11ed-11ed-861d-0242ac120002'),
        (2,'resources/cv/cv_001.pdf', 2, 1, 12,'8f428c22-11ed-11ed-861d-0242ac120002'),
        (3,'resources/cv/cv_002.pdf', 3, 2, 13,'962d2b46-11ed-11ed-861d-0242ac120002'),
        (4,'resources/cv/cv_003.pdf', 2, 3, 14,'c0be68e4-e86d-4389-a8af-65277b27345e'),
        (5,'resources/cv/cv_004.pdf', 1, 4, 16,'b7f68596-6777-49d1-aeb7-8c1a522a8bf3'),
        (6,'resources/cv/cv_005.pdf', 5, 5, 12,'c8760776-d0aa-4040-88a3-4f2e13a39145');


INSERT INTO management_documentation VALUES(
1,'RGSID_1', 'Frontend developer', '2022-01-13', 'John Doe', 'he fits into the team as a frontend candidate', FALSE,
TRUE, 'learning the usage of react', 'he has a lot of motivation for the position', 'high', 'none', 'excellent knowlegde in HTML and Javascript',
'lack of knowlege when it comes to react', 'none', 'very proactive', TRUE, 1, 'he fits well enough', 2, '2006cb02-118e-11ed-861d-0242ac120002',
1, 4, 6, 2
);

INSERT INTO management_documentation VALUES(
2,'RGSID_2', 'backend developer', '2022-01-15', 'Csaba Hegedus', 'she fits', TRUE,
TRUE, 'to study spring boot', 'none', 'high', 'none', 'avarege java knowlegde',
'avarege java knowlegde', 'none', 'not proactive at all', FALSE, 1, 'she does not fit', 1, '2006cb02-118e-11ed-861d-0242ac120004',
2, 5, 8, 2
);

INSERT INTO management_documentation VALUES(
3,'RGSID_3', 'Backend developer', '2022-01-12', 'William Howard', 'she fits', FALSE,
TRUE, 'learning the usage of react', 'none', 'low', 'none', 'excellent knowlegde in HTML, Javascript, Angular and React',
'cant speak english', 'none', 'somewhat proactive and interested', TRUE, 2, 'she fits into the team', 4, '65f00650-c2c1-4253-992f-bedee28197dc',
3, 1, 4, 3
);

INSERT INTO management_documentation VALUES(
4,'RGSID_4', 'Frontend developer', '2022-04-25', 'Csaba Hegedus', 'he fits', TRUE,
TRUE, 'he want sto work in a multicultural enviroment', 'he has a lot of experience', 'high', 'none', 'his experience gives him an edge when it comes to frontend development',
'cant speak english', 'none', 'very proactive', TRUE, 3, 'he fits into the team as a frontend candidate', 4, '364a8600-118f-11ed-861d-0242ac120002',
5, 4, 7, 4
);


INSERT INTO management_documentation VALUES(
5,'RGSID_5', 'Fullstack programmer', '2022-04-28', 'Csaba Hegedus', 'he fits', TRUE,
TRUE, 'he is open for improving both his frontend and backend knowledge', 'he has a lot of experience', 'high', 'none', 'very skilled with spring boot ang react as well',
'lack of experience with agile', 'none', 'very proactive', TRUE, 3, 'he fits into the team', 3, '44245670-118f-11ed-861d-0242ac120002',
6, 2, 3, 1
);

