CREATE TABLE countries
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE cities
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    country_id INTEGER REFERENCES countries (id)
);

INSERT INTO countries(name)
VALUES ('Россия'),
       ('Германия'),
       ('Франция'),
       ('Испания');

INSERT INTO cities(name, country_id)
VALUES ('Москва', 1),
       ('Санкт-Петербург', 1),
       ('Новосибирск', 1),
       ('Берлин', 2),
       ('Мюнхен', 2),
       ('Гамбург', 2),
       ('Париж', 3),
       ('Лион', 3),
       ('Марсель', 3),
       ('Мадрид', 4),
       ('Барселона', 4),
       ('Валенсия', 4);

INSERT INTO cities(name)
VALUES ('Минск'),
       ('Киев'),
       ('Астана');

SELECT ci.name AS Город, c.name AS Страна
FROM cities ci
         JOIN countries c ON ci.country_id = c.id;

SELECT ci.name AS "Мой город", c.name AS "Мои страны"
FROM cities AS ci
         JOIN countries AS c ON ci.country_id = c.id;

SELECT страны.name AS "Ваши страны", города.name AS "Ваши города"
FROM countries AS страны
         JOIN cities AS города ON города.country_id = страны.id