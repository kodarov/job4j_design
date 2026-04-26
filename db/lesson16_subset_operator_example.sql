CREATE DATABASE lesson16_example;

CREATE TABLE customers
(
    first_name TEXT,
    last_name  TEXT,
    status     TEXT
);

CREATE TABLE employees
(
    first_name TEXT,
    last_name  TEXT,
    emp_status TEXT
);

INSERT INTO customers
VALUES ('Иван', 'Иванов', 'Active'),
       ('Петр', 'Сергеев', 'Active'),
       ('Ирина', 'Бросова', 'Active'),
       ('Анна', 'Опушкина', 'Active'),
       ('Потап', 'Потапов', 'Passive');

INSERT INTO employees
VALUES ('Кристина', 'Позова', 'Current'),
       ('Михаил', 'Кругов', 'Current'),
       ('Анна', 'Опушкина', 'Current'),
       ('Иван', 'Иванов', 'Current'),
       ('Сергей', 'Петров', 'Current');

SELECT first_name, last_name
FROM customers
UNION
SELECT first_name, last_name
FROM employees;

SELECT first_name, last_name
FROM customers
UNION
SELECT first_name, last_name
FROM employees
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customers
WHERE status = 'Active'
UNION
SELECT first_name, last_name
FROM employees
WHERE emp_status = 'Current'
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customers
UNION ALL
SELECT first_name, last_name
FROM employees;

SELECT first_name, last_name
FROM customers
EXCEPT
SELECT first_name, last_name
FROM employees;

SELECT first_name, last_name
FROM customers
INTERSECT
SELECT first_name, last_name
FROM employees;

-----------------------------------------------------------

CREATE TABLE referrers
(
    first_name text,
    last_name  text
);

INSERT INTO referrers
VALUES ('Евгений', 'Онегин'),
       ('Петр', 'Сергеев'),
       ('Александр', 'Ожегов'),
       ('Анна', 'Опушкина'),
       ('Михаил', 'Кругов');

SELECT first_name, last_name
FROM customers
UNION
SELECT first_name, last_name
FROM employees
UNION
SELECT first_name, last_name
FROM referrers
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customers
UNION ALL
SELECT first_name, last_name
FROM employees
EXCEPT
SELECT first_name, last_name
FROM referrers
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customers
UNION ALL
(SELECT first_name, last_name
 FROM employees
 EXCEPT
 SELECT first_name, last_name
 FROM referrers)
ORDER BY first_name, last_name;