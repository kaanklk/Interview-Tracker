SET session_replication_role = 'replica';

insert into position(id, position_name, project_id, total_count, hired_count, open, uuid)
values
    (10, 'Junior Java Developer', 1, 7, 3, true, 'b9dc902a-11a9-11ed-861d-0242ac120002'),
    (12,'Junior C# Developer', 2, 10, 5, true, '3e157c50-1306-11ed-861d-0242ac120002'),
    (13,'Senior Java Developer', 3, 11, 9, false, '438a7cd0-1306-11ed-861d-0242ac120002'),
    (14,'Junior Data Engineer', 4, 5, 3, true, '46dc6b1e-1306-11ed-861d-0242ac120002'),
    (15, 'Medor Python Developer', 5, 4, 3, false, '4ddc35d4-1306-11ed-861d-0242ac120002'),
    (16, 'Junior HR Assistant', 3, 10, 3, true, '54fafe36-1306-11ed-861d-0242ac120002');

INSERT INTO project (id, name, description, deadline , uuid)
    VALUES (1, 'alpha','This is a project to build a new developer team', 2022 - 06 - 20, '79a7f7a0-1196-11ed-861d-0242ac120002'), (2, 'beta','This is a project to build a new hr team', 2022 - 01 - 23, '80903b22-1196-11ed-861d-0242ac120002'), (3, 'gamma','This is a project to build a new data engineer team', 2022 - 01 - 23, 'b2eeb4e0-1196-11ed-861d-0242ac120002'), (4, 'epsilon', 'This is a project to build a new frontend team', 2022 - 01 - 23, 'b7c1b364-1196-11ed-861d-0242ac120002'), (5, 'omega','This is a project to build a new backend team', 2022 - 01 - 23, 'bb65fade-1196-11ed-861d-0242ac120002');

INSERT INTO users (id, admin, created_at, date_of_birth, email, employee_id, first_name, last_name, middle_name, phone_number, photo, uuid)
    VALUES (1, TRUE, now(), '1997-02-02', 'john.doe1@mail.ru', '1234567', 'John1', 'Doe1', 'Diego1', '+3687238323', 'url1', 'dfb79789-98ff-4709-b2a2-f9aad760f1e1'), (2, TRUE, now(), '1998-02-02', 'john.doe2@mail.ru', '4234567', 'John2', 'Doe2', 'Diego2', '+3687238323', 'url2', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1'), (3, FALSE, now(), '1999-02-02', 'john.doe3@mail.ru', '1236567', 'John3', 'Doe3', 'Diego3', '+3687238323', 'url3', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2'), (4, TRUE, now(), '1992-02-02', 'john.doe4@mail.ru', '1234727', 'John4', 'Doe4', 'Diego4', '+3687238323', 'url4', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3'), (5, TRUE, now(), '1993-02-02', 'john.doe5@mail.ru', '6734567', 'John5', 'Doe5', 'Diego5', '+3687238323', 'url5', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4'), (6, FALSE, now(), '1991-02-02', 'john.doe6@mail.ru', '1454567', 'John6', 'Doe6', 'Diego6', '+3687238323', 'url6', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5'), (7, TRUE, now(), '1996-02-02', 'john.doe7@mail.ru', '1234567', 'John7', 'Doe7', 'Diego7', '+3687238323', 'url7', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e6'), (8, FALSE, now(), '1994-02-02', 'john.doe8@mail.ru', '3454567', 'John8', 'Doe8', 'Diego8', '+3687238323', 'url8', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e7'), (9, TRUE, now(), '1995-02-02', 'john.doe9@mail.ru', '4344567', 'John9', 'Doe9', 'Diego9', '+3687238323', 'url9', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e8'), (10, FALSE, now(), '1993-02-02', 'john.doe0@mail.ru', '8754567', 'John10', 'Doe10', 'Diego10', '+3687238323', 'url10', 'dfb79789-98ff-4709-b2a2-f9a6da60f1e8');

INSERT INTO roles (role_name, uuid)
    VALUES ('Management Interviewer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e1'), ('Project Manager', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e2'), ('Recruiter', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e3'), ('Sourcer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e4'), ('Technical Interviewer', 'dfb79789-98ff-4709-b2a2-f9a6d760f1e5');

INSERT INTO user_roles (user_id, project_id)
    VALUES (1, 1), (2, 2), (3, 3), (4, 4), (4, 5);

INSERT INTO user_roles_role (user_roles_id, role_id)
    VALUES (1, 2), (1, 3), (2, 5), (3, 1), (4, 4);
