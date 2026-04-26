CREATE DATABASE lesson15_example;

CREATE TABLE customers
(
    id         SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INT,
    country    TEXT
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Анна', 'Иванова', 28, 'Россия'),
       ('Дмитрий', 'Соколов', 35, 'Россия'),
       ('Елена', 'Кузнецова', 42, 'Россия'),
       ('Джон', 'Смит', 34, 'США'),
       ('Эмма', 'Джонсон', 28, 'США'),
       ('Софи', 'Мартен', 25, 'Франция'),
       ('Пьер', 'Дюбуа', 38, 'Франция'),
       ('Ганс', 'Шмидт', 47, 'Германия'),
       ('Анна', 'Вебер', 31, 'Германия'),
       ('Лука', 'Росси', 29, 'Италия'),
       ('Джулия', 'Риччи', 33, 'Италия'),
       ('Карлос', 'Гарсия', 36, 'Испания'),
       ('Люсия', 'Фернандес', 27, 'Испания'),
       ('Вэй', 'Чжан', 30, 'Китай'),
       ('Мэй', 'Ли', 26, 'Китай'),
       ('Юки', 'Танака', 32, 'Япония'),
       ('Харуки', 'Сато', 24, 'Япония');


SELECT *
FROM customers c
WHERE c.age = (SELECT MIN(age)
               FROM customers с);

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT REFERENCES customers (id)
);

INSERT INTO orders (amount, customer_id)
VALUES (1200, 1),
       (3400, 1),
       (2800, 2),
       (1500, 3),
       (4600, 4),
       (2100, 5),
       (3900, 5),
       (1750, 6),
       (5200, 7),
       (3100, 8),
       (1700, 15),
       (2900, 16),
       (1350, 17);


SELECT *
FROM customers c
WHERE c.id NOT IN (SELECT o.customer_id
                   FROM orders o);

