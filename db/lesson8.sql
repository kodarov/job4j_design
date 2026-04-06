CREATE DATABASE lesson8_db;

CREATE TABLE car_bodies
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_engines
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_transmissions
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    body_id         INT REFERENCES car_bodies (id),
    engine_id       INT REFERENCES car_engines (id),
    transmission_id INT REFERENCES car_transmissions (id)
);

INSERT INTO car_bodies (name)
VALUES ('Седан'),
       ('Хэтчбек'),
       ('Внедорожник'),
       ('Купе'),
       ('Пикап'),
       ('Универсал'),
       ('Кабриолет'),
       ('Минивэн'),
       ('Лимузин');

INSERT INTO car_engines (name)
VALUES ('1.4 бензин'),
       ('1.6 бензин'),
       ('2.0 бензин'),
       ('2.0 дизель'),
       ('2.5 бензин'),
       ('3.0 бензин'),
       ('1.8 гибрид'),
       ('Электро'),
       ('4.0 бензин'),
       ('Водородный');

INSERT INTO car_transmissions (name)
VALUES ('Механика 5'),
       ('Механика 6'),
       ('Автомат 6'),
       ('Автомат 8'),
       ('Вариатор'),
       ('Робот'),
       ('Автомат 10'),
       ('Секвентальная');

INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Лада Веста', 1, 2, 1),
       ('Киа Рио', 1, 2, 3),
       ('Шкода Октавия', 6, 3, 3),
       ('Фольксваген Гольф', 2, 3, 2),
       ('Рено Сандеро', 2, 1, 1),
       ('Тойота Камри', 1, 5, 4),
       ('Мазда 6', 1, 3, 3),
       ('БМВ 3', 1, 5, 4),
       ('Ауди А5', 4, 5, 6),
       ('Форд Мустанг', 4, 6, 4),
       ('Лада Ларгус', 8, 2, 1),
       ('Фольксваген Туран', 8, 3, 3),
       ('Хендай Туссан', 3, 3, 3),
       ('Киа Спортейдж', 3, 3, 5),
       ('Ниссан Кашкай', 3, 4, 5),
       ('Тойота РАВ4', 3, 7, 5),
       ('УАЗ Патриот', 3, 3, 2),
       ('Митсубиси L200', 5, 4, 2),
       ('Тойота Хайлюкс', 5, 4, 1),
       ('Мазда MX-5', 7, 2, 1),
       ('БМВ Z4', 7, 5, 4),
       ('Тесла Модель 3', 1, 8, 4),
       ('Ниссан Лиф', 2, 8, 5),
       ('Хавал F7', 3, 3, 6),
       ('Проект X1', NULL, 3, 3),
       ('Проект X2', 1, NULL, 4),
       ('Проект X3', 2, 2, NULL),
       ('Прототип Z', NULL, NULL, NULL);

SELECT b.name кузов
FROM car_bodies b
         LEFT JOIN car c on b.id = c.body_id
WHERE c.id IS NULL;

SELECT e.name двигатель
FROM car_engines e
         LEFT JOIN car c on e.id = c.engine_id
WHERE c.id IS NULL;

SELECT t.name коробка_передач
FROM car_transmissions t
         LEFT JOIN car c on t.id = c.transmission_id
WHERE c.id IS NULL;


