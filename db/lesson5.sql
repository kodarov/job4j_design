CREATE TABLE devices
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT
);

CREATE TABLE people
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people
(
    id        SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices (id),
    people_id INT REFERENCES people (id)
);

INSERT INTO devices(name, price)
VALUES ('iPhone 15', 999),
       ('Samsung Galaxy S24', 899),
       ('MacBook Air M3', 1299),
       ('iPad Air', 699),
       ('Apple Watch', 399),
       ('Google Pixel 8', 799),
       ('Xiaomi 14', 749),
       ('Lenovo ThinkPad X1', 1599),
       ('Dell XPS 13', 1499),
       ('Sony WH-1000XM5', 349),
       ('Kindle Paperwhite', 189),
       ('Nintendo Switch', 299);

INSERT INTO people(name)
VALUES ('Ivan'),
       ('Petr'),
       ('Anna'),
       ('Olga'),
       ('Sergey'),
       ('Maria'),
       ('Dmitry'),
       ('Elena'),
       ('Nikita'),
       ('Svetlana');

INSERT INTO devices_people(device_id, people_id)
VALUES (1, 1),
       (3, 1),
       (2, 2),
       (4, 3),
       (1, 4),
       (5, 4),
       (6, 5),
       (7, 5),
       (8, 6),
       (9, 7),
       (10, 7),
       (11, 8),
       (12, 9),
       (3, 9),
       (2, 10),
       (5, 10);

SELECT avg(d.price) AS "Средняя цена"
FROM devices d;

SELECT p.name AS "Имя", avg(d.price) AS "Средняя цена устройств"
FROM people p
         JOIN devices_people dp on p.id = dp.people_id
         JOIN devices d on dp.device_id = d.id
GROUP BY p.name;

SELECT p.name AS "Имя", avg(d.price) AS "Средняя цена устройств"
FROM people p
         JOIN devices_people dp on p.id = dp.people_id
         JOIN devices d on dp.device_id = d.id
GROUP BY p.name
HAVING avg(d.price) > 1000;


