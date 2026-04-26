CREATE DATABASE lesson16_example;

CREATE TABLE movies
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    director TEXT
);

CREATE TABLE books
(
    id     SERIAL PRIMARY KEY,
    title  TEXT,
    author TEXT
);

INSERT INTO movies (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO books (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

SELECT m.name AS "фильмы сняты по книге"
FROM movies m
INTERSECT
SELECT b.title
FROM books b;

SELECT b.title AS "книги без экранизации"
FROM books b
EXCEPT
SELECT m.name
FROM movies m;

(SELECT m.name AS "уникальные фильмы и книги"
 FROM movies m
 EXCEPT
 SELECT b.title
 FROM books b)
UNION
(SELECT b.title AS "книги без экранизации"
 FROM books b
 EXCEPT
 SELECT m.name
 FROM movies m);










