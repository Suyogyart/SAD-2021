
insert into User (username, password, role, active) values ('suyogya', '$2y$12$8uukLpsrEgAwY3azdXTGOuWpAAPZA7LyB50G4g9kHP2NnrRUB9wTS', 'ROLE_ADMIN', true);
insert into User (username, password, role, active) values ('chaky', '$2y$12$8uukLpsrEgAwY3azdXTGOuWpAAPZA7LyB50G4g9kHP2NnrRUB9wTS', 'ROLE_ADMIN', true);


insert into Employee (user_id, name, level, birthday, base_salary) values (1, 'Suyogya', 'C3', '1994-02-11', 52000);
insert into Employee (user_id, name, level, birthday, base_salary) values (2, 'Chaklam', 'C2', '2012-10-10', 12000);


insert into Address (city, street, house_no, zipcode, emp_user_id) values ('Kathmandu', 'Khusibu', '123/31', '44600', 1);
insert into Address (city, street, house_no, zipcode, emp_user_id) values ('Khlong Luang', 'AIT', '31', '43234', 2);