insert into Company (name) values ('Company A');
insert into Company (name) values ('Company B');

insert into Categories (name) values ('Category A');
insert into Categories (name) values ('Category B');

insert into Product (name, price, stock, company_id, version) values ('Product 1', 100.0, 10, 1, 0);
insert into Product (name, price, stock, company_id, version) values ('Product 2', 300.0, 4, 1, 0);
insert into Product (name, price, stock, company_id, version) values ('Product 3', 500.0, 7, 2, 0);
insert into Product (name, price, stock, company_id, version) values ('Product 4', 1000.0, 15, 1, 0);
insert into Product (name, price, stock, company_id, version) values ('Product 5', 700, 2, 2, 0);
insert into Product (name, price, stock, company_id, version) values ('Product 6', 3000, 5, 2, 0);

insert into product_categories (products_id, categories_id) values (1, 1);
insert into product_categories (products_id, categories_id) values (1, 2);
insert into product_categories (products_id, categories_id) values (2, 1);
insert into product_categories (products_id, categories_id) values (3, 1);
insert into product_categories (products_id, categories_id) values (4, 2);
insert into product_categories (products_id, categories_id) values (5, 2);
insert into product_categories (products_id, categories_id) values (6, 2);


