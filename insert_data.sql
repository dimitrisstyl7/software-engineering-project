-- admin -> username: admin, password: admin
-- patient -> username: aliceWhite, password: aliceWhite
-- doctor -> username: bobMarley, password: bobMarley
insert into user_tbl
values ('admin', 'admin@dev.com', 'John', '$2a$10$OeqThz6N4i8rymWRpsmPAuv70ahFw5rNbNSWFwiU6qSrwkZKqJR9C',
        '1234447890', 'Brown', 'admin'),
       ('aliceWhite', 'alicewhite@dev.com', 'Alice', '$2a$10$7hhqUKi/LZJCLcbwtzLYJuUpOLgHnsnDb.z6FUI11edUxvvourvLK',
        '1221434567', 'White', 'patient'),
       ('bobMarley', 'bobmarley@dev.com', 'Bob', '$2a$10$G/ZmCQoJ4skKpVy/A1Ve/.UEabBj/7KX3WkkCCP0ztcyGgpHgMN.K',
        '1234567890', 'Marley', 'doctor');

insert into admin_tbl (user_username)
values ('admin');

insert into patient_tbl
values ('12345678908', '1990-01-01', now(), 'aliceWhite');

insert into doctor_tbl (afm, address, business_phone, city, date_of_birth, registered_on, specialization, status, user_username)
values ('123456789', '2434 Paradise Lane', '+304567934910', 'Paradise', '1990-01-01', now(), 'Epidemiologist', 'approved', 'bobMarley');