CREATE DATABASE lesson7_db;

CREATE TABLE departments
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255),
    depart_id INT REFERENCES departments (id)
);

INSERT INTO departments (name)
VALUES ('HR'),
       ('Engineering'),
       ('Marketing'),
       ('Finance'),
       ('Support'),
       ('Legal'),
       ('R&D');

INSERT INTO employees (name, depart_id)
VALUES ('Ivan Petrov', 1),
       ('Anna Smirnova', 1),
       ('Pavel Sidorov', 2),
       ('Maria Kuznetsova', 2),
       ('Alexey Volkov', 2),
       ('Olga Fedorova', 3),
       ('Dmitry Orlov', 4),
       ('Elena Pavlova', 5),
       ('Roman Belov', NULL),
       ('Irina Lebedeva', NULL);

--2. Все сотрудники
SELECT e.name Имя, d.name Отдел
FROM employees e
         LEFT JOIN departments d ON e.depart_id = d.id;

SELECT e.name Имя, d.name Отдел
FROM departments d
         RIGHT JOIN employees e ON e.depart_id = d.id;


--2. Все отделы
SELECT d.name Отдел, e.name Имя
FROM departments d
         LEFT JOIN employees e ON e.depart_id = d.id;

SELECT d.name Отдел, e.name Имя
FROM employees e
         RIGHT JOIN departments d ON e.depart_id = d.id;

--2. Просто все
SELECT e.name Имя, d.name Отдел
FROM employees e
         FULL JOIN departments d ON e.depart_id = d.id;

--2. Всевозможные варианты
SELECT e.name Имя, d.name Отдел
FROM employees e
         CROSS JOIN departments d;

--3. Пустые отделы
SELECT d.name пустые_отделы
FROM departments d
         LEFT JOIN employees e on d.id = e.depart_id
WHERE e.id IS NULL;

--4. left/right join
SELECT e.name Имя, d.name Отдел
FROM employees e
         LEFT JOIN departments d ON e.depart_id = d.id
ORDER BY e.name;

SELECT e.name Имя, d.name Отдел
FROM departments d
         RIGHT JOIN employees e ON e.depart_id = d.id
ORDER BY e.name;

--5.
CREATE TABLE teens
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(255),
    gender VARCHAR(255)
);

INSERT INTO teens (name, gender)
VALUES ('Вася', 'м'),
       ('Маша', 'ж'),
       ('Петя', 'м'),
       ('Катя', 'ж'),
       ('Саша', 'м'),
       ('Оля', 'ж'),
       ('Дима', 'м'),
       ('Лена', 'ж');

SELECT b.name мальчики, g.name девочки
FROM (SELECT *
      FROM teens t
      WHERE t.gender = 'м') b
         CROSS JOIN (SELECT *
                     FROM teens t
                     WHERE t.gender = 'ж') g;








