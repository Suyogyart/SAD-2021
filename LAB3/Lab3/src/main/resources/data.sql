insert into user (username, password, active, role) values ('chaklam', '$2y$12$2LA4/IzwsfoF.SFtdxwJIus48N6JwFzTdMwlrc9lXRHnA9EOBU7AS', true, 'ROLE_ADMIN');
insert into user (username, password,  active, role) values ('john', '$2y$10$uwslZFS.czXR6VE7XzyBTuH.xGtdDmBBnioj2J/KZqJr0cHDEr3fa', true, 'ROLE_USER');
insert into user (username, password,  active, role) values ('peter', '$2y$10$uwslZFS.czXR6VE7XzyBTuH.xGtdDmBBnioj2J/KZqJr0cHDEr3fa', true, 'ROLE_USER');
insert into user (username, password,  active, role) values ('suyogya', '$2y$12$8uukLpsrEgAwY3azdXTGOuWpAAPZA7LyB50G4g9kHP2NnrRUB9wTS', true, 'ROLE_ADMIN');

insert into employee_info (employee_age, fname, lname, mname, user_id) values (20, 'Chaklam', 'Silpasuwanchai', ' ', 1);
insert into employee_info (employee_age, fname, lname, mname, user_id) values (69, 'John', 'Cahill', ' ', 2);

insert into benefit(title) values ('Benefit Free Water');
insert into benefit(title) values ('Benefit Free Coffee');

insert into employee_info_benefits (emp_user_id, benefits_id) values (1, 1);
insert into employee_info_benefits (emp_user_id, benefits_id) values (1, 2);
insert into employee_info_benefits (emp_user_id, benefits_id) values (2, 1);

insert into address (city, house_no, street_address, zipcode, emp_user_id) values ('Bangkok', '30/6', 'Ramindra', '10220', 1);
insert into address (city, house_no, street_address, zipcode, emp_user_id) values ('Bangkok', '30/7', 'Victory Monument', '12220', 2);