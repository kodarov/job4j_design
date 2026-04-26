CREATE DATABASE lesson15_example;

CREATE TABLE companies
(
    id   INT PRIMARY KEY,
    city TEXT
);

CREATE TABLE goods
(
    id         INT PRIMARY KEY,
    name       TEXT,
    company_id INT REFERENCES companies (id),
    price      INT
);

CREATE TABLE sales_managers
(
    id          INT PRIMARY KEY,
    last_name   TEXT,
    first_name  TEXT,
    company_id  INT REFERENCES companies (id),
    manager_fee INT
);

CREATE TABLE managers
(
    id         INT PRIMARY KEY,
    company_id INT REFERENCES companies (id)
);

INSERT INTO companies
VALUES (1, 'Москва'),
       (2, 'Нью-Йорк'),
       (3, 'Мюнхен');

INSERT INTO goods
VALUES (1, 'Небольшая квартира', 3, 5000),
       (2, 'Квартира в центре', 1, 4500),
       (3, 'Квартира у метро', 1, 3200),
       (4, 'Лофт', 2, 6700),
       (5, 'Загородный дом', 2, 9800);

INSERT INTO sales_managers
VALUES (1, 'Доу', 'Джон', 2, 2250),
       (2, 'Грубер', 'Ганс', 3, 3120),
       (3, 'Смит', 'Сара', 2, 1640),
       (4, 'Иванов', 'Иван', 1, 4500),
       (5, 'Купер', 'Грета', 3, 2130);

INSERT INTO managers
VALUES (1, 2),
       (2, 3),
       (4, 1);

SELECT *
FROM sales_managers
WHERE manager_fee > (SELECT AVG(manager_fee) FROM sales_managers);

SELECT AVG(manager_fee)
FROM sales_managers
WHERE sales_managers.id NOT IN (SELECT managers.id FROM managers);

SELECT city,
       (SELECT COUNT(*)
        FROM goods AS g
        WHERE c.id = g.company_id) AS total_goods
FROM companies c;

SELECT last_name, first_name, manager_fee
FROM sales_managers sm1
WHERE sm1.manager_fee >= (SELECT AVG(manager_fee)
                          FROM sales_managers sm2
                          WHERE sm2.company_id = sm1.company_id);

SELECT company_id, AVG(price) AS average_price
FROM goods
GROUP BY company_id
HAVING AVG(price) > (SELECT MAX(price) FROM goods) / 2;