CREATE TABLE employees
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
    age  INTEGER
);

INSERT INTO employees (name, age)
VALUES ('ivan', 20),
       ('alex', 32),
       ('petr', 35)
;

UPDATE employees
SET age = 25
WHERE name = 'ivan';

SELECT *
FROM employees;

DELETE
FROM employees
WHERE name = 'ivan';

SELECT *
FROM employees;