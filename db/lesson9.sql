CREATE DATABASE lesson9_db;

CREATE TABLE faculties
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(120) NOT NULL UNIQUE,
    short_name VARCHAR(20)  NOT NULL UNIQUE,
    dean       VARCHAR(120) NOT NULL
);

CREATE TABLE student_groups
(
    id             SERIAL PRIMARY KEY,
    code           VARCHAR(20)  NOT NULL UNIQUE,
    faculty_id     INTEGER      NOT NULL REFERENCES faculties (id),
    admission_year INTEGER      NOT NULL CHECK (admission_year >= 2000),
    curator        VARCHAR(120) NOT NULL
);

CREATE TABLE students
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(120) NOT NULL,
    birth_date      DATE         NOT NULL,
    email           VARCHAR(120) NOT NULL UNIQUE,
    phone           VARCHAR(20)  NOT NULL UNIQUE,
    city            VARCHAR(80)  NOT NULL,
    enrollment_year INTEGER      NOT NULL CHECK (enrollment_year >= 2000),
    group_id        INTEGER      NOT NULL REFERENCES student_groups (id)
);

CREATE TABLE authors
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(120) NOT NULL UNIQUE,
    country    VARCHAR(80)  NOT NULL,
    birth_year INTEGER,
    death_year INTEGER
);

CREATE TABLE books
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(200) NOT NULL,
    genre        VARCHAR(80)  NOT NULL,
    publish_year INTEGER,
    isbn         VARCHAR(30) UNIQUE,
    copies_total INTEGER      NOT NULL DEFAULT 1 CHECK (copies_total > 0),
    author_id    INTEGER      NOT NULL REFERENCES authors (id)
);

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    active      BOOLEAN NOT NULL DEFAULT true,
    order_date  DATE    NOT NULL,
    due_date    DATE    NOT NULL,
    return_date DATE,
    book_id     INTEGER NOT NULL REFERENCES books (id),
    student_id  INTEGER NOT NULL REFERENCES students (id),
    CHECK (due_date >= order_date),
    CHECK (return_date IS NULL OR return_date >= order_date)
);

INSERT INTO faculties (name, short_name, dean)
VALUES ('Факультет информационных технологий', 'ФИТ', 'Петров Сергей Николаевич'),
       ('Факультет экономики и управления', 'ФЭУ', 'Иванова Наталья Викторовна'),
       ('Факультет гуманитарных наук', 'ФГН', 'Смирнов Алексей Павлович'),
       ('Факультет прикладной математики', 'ФПМ', 'Козлова Ольга Дмитриевна');

INSERT INTO student_groups (code, faculty_id, admission_year, curator)
VALUES ('ИТ-101', 1, 2023, 'Волкова Марина Андреевна'),
       ('ИТ-102', 1, 2023, 'Зайцев Илья Романович'),
       ('ЭК-201', 2, 2022, 'Громов Денис Сергеевич'),
       ('ЭК-202', 2, 2022, 'Лебедева Татьяна Игоревна'),
       ('ГУМ-301', 3, 2021, 'Беляев Роман Евгеньевич'),
       ('ГУМ-302', 3, 2021, 'Орлова Елена Сергеевна'),
       ('ПМ-401', 4, 2020, 'Мельников Антон Павлович'),
       ('ПМ-402', 4, 2020, 'Тихонова Ирина Михайловна');

INSERT INTO students (name, birth_date, email, phone, city, enrollment_year, group_id)
VALUES ('Иванов Иван Иванович', '2005-02-12', 'ivanov.ii@univer.ru', '+7-901-111-11-01', 'Москва', 2023, 1),
       ('Петров Пётр Александрович', '2004-10-03', 'petrov.pa@univer.ru', '+7-901-111-11-02', 'Тула', 2023, 1),
       ('Смирнова Анна Дмитриевна', '2005-05-26', 'smirnova.ad@univer.ru', '+7-901-111-11-03', 'Калуга', 2023, 2),
       ('Кузнецов Максим Игоревич', '2004-12-19', 'kuznecov.mi@univer.ru', '+7-901-111-11-04', 'Ярославль', 2023, 2),
       ('Попова Екатерина Сергеевна', '2003-08-08', 'popova.es@univer.ru', '+7-901-111-11-05', 'Рязань', 2022, 3),
       ('Васильев Никита Олегович', '2003-01-15', 'vasilev.no@univer.ru', '+7-901-111-11-06', 'Воронеж', 2022, 3),
       ('Соколов Артём Андреевич', '2003-06-21', 'sokolov.aa@univer.ru', '+7-901-111-11-07', 'Липецк', 2022, 4),
       ('Морозова Мария Владимировна', '2002-11-11', 'morozova.mv@univer.ru', '+7-901-111-11-08', 'Тверь', 2022, 4),
       ('Новиков Денис Евгеньевич', '2002-03-09', 'novikov.de@univer.ru', '+7-901-111-11-09', 'Смоленск', 2021, 5),
       ('Фёдорова Полина Алексеевна', '2002-09-27', 'fedorova.pa@univer.ru', '+7-901-111-11-10', 'Курск', 2021, 5),
       ('Белов Роман Николаевич', '2001-07-17', 'belov.rn@univer.ru', '+7-901-111-11-11', 'Орел', 2021, 6),
       ('Лазарева Дарья Ильинична', '2002-01-29', 'lazareva.di@univer.ru', '+7-901-111-11-12', 'Брянск', 2021, 6),
       ('Крылов Егор Павлович', '2001-04-30', 'krylov.ep@univer.ru', '+7-901-111-11-13', 'Москва', 2020, 7),
       ('Комарова Ольга Геннадьевна', '2001-12-05', 'komarova.og@univer.ru', '+7-901-111-11-14', 'Тула', 2020, 7),
       ('Егоров Алексей Романович', '2000-10-25', 'egorov.ar@univer.ru', '+7-901-111-11-15', 'Казань', 2020, 8),
       ('Николаева Софья Ивановна', '2001-09-14', 'nikolaeva.si@univer.ru', '+7-901-111-11-16', 'Самара', 2020, 8),
       ('Орлов Глеб Михайлович', '2005-03-06', 'orlov.gm@univer.ru', '+7-901-111-11-17', 'Иваново', 2023, 1),
       ('Захарова Виктория Петровна', '2004-11-18', 'zaharova.vp@univer.ru', '+7-901-111-11-18', 'Кострома', 2023, 2),
       ('Быков Константин Юрьевич', '2003-02-24', 'bykov.ky@univer.ru', '+7-901-111-11-19', 'Белгород', 2022, 3),
       ('Тимофеева Алина Олеговна', '2002-06-16', 'timofeeva.ao@univer.ru', '+7-901-111-11-20', 'Пенза', 2021, 6);

INSERT INTO authors (name, country, birth_year, death_year)
VALUES ('Александр Пушкин', 'Россия', 1799, 1837),
       ('Николай Гоголь', 'Россия', 1809, 1852),
       ('Лев Толстой', 'Россия', 1828, 1910),
       ('Фёдор Достоевский', 'Россия', 1821, 1881),
       ('Иван Тургенев', 'Россия', 1818, 1883),
       ('Антон Чехов', 'Россия', 1860, 1904),
       ('Михаил Булгаков', 'Россия', 1891, 1940),
       ('Михаил Лермонтов', 'Россия', 1814, 1841),
       ('Иван Гончаров', 'Россия', 1812, 1891),
       ('Николай Лесков', 'Россия', 1831, 1895),
       ('Максим Горький', 'Россия', 1868, 1936),
       ('Иван Бунин', 'Россия', 1870, 1953);

INSERT INTO books (title, genre, publish_year, isbn, copies_total, author_id)
VALUES ('Евгений Онегин', 'Роман в стихах', 1833, '978-5-00111-000-1', 8, 1),
       ('Капитанская дочка', 'Исторический роман', 1836, '978-5-00111-000-2', 7, 1),
       ('Дубровский', 'Роман', 1841, '978-5-00111-000-3', 6, 1),
       ('Мёртвые души', 'Поэма', 1842, '978-5-00111-000-4', 9, 2),
       ('Вий', 'Повесть', 1835, '978-5-00111-000-5', 5, 2),
       ('Ревизор', 'Комедия', 1836, '978-5-00111-000-6', 6, 2),
       ('Война и мир', 'Роман-эпопея', 1869, '978-5-00111-000-7', 12, 3),
       ('Анна Каренина', 'Роман', 1877, '978-5-00111-000-8', 10, 3),
       ('Воскресение', 'Роман', 1899, '978-5-00111-000-9', 6, 3),
       ('Преступление и наказание', 'Роман', 1866, '978-5-00111-001-0', 11, 4),
       ('Идиот', 'Роман', 1869, '978-5-00111-001-1', 8, 4),
       ('Братья Карамазовы', 'Роман', 1880, '978-5-00111-001-2', 7, 4),
       ('Отцы и дети', 'Роман', 1862, '978-5-00111-001-3', 9, 5),
       ('Дворянское гнездо', 'Роман', 1859, '978-5-00111-001-4', 4, 5),
       ('Вишнёвый сад', 'Пьеса', 1904, '978-5-00111-001-5', 5, 6),
       ('Палата № 6', 'Повесть', 1892, '978-5-00111-001-6', 6, 6),
       ('Мастер и Маргарита', 'Роман', 1967, '978-5-00111-001-7', 12, 7),
       ('Собачье сердце', 'Повесть', 1925, '978-5-00111-001-8', 10, 7),
       ('Герой нашего времени', 'Роман', 1840, '978-5-00111-001-9', 7, 8),
       ('Мцыри', 'Поэма', 1840, '978-5-00111-002-0', 5, 8),
       ('Обломов', 'Роман', 1859, '978-5-00111-002-1', 8, 9),
       ('Обыкновенная история', 'Роман', 1847, '978-5-00111-002-2', 4, 9),
       ('Левша', 'Повесть', 1881, '978-5-00111-002-3', 6, 10),
       ('Очарованный странник', 'Повесть', 1873, '978-5-00111-002-4', 5, 10),
       ('На дне', 'Пьеса', 1902, '978-5-00111-002-5', 7, 11),
       ('Детство', 'Повесть', 1913, '978-5-00111-002-6', 5, 11),
       ('Тёмные аллеи', 'Сборник рассказов', 1943, '978-5-00111-002-7', 6, 12),
       ('Жизнь Арсеньева', 'Роман', 1930, '978-5-00111-002-8', 4, 12);

INSERT INTO orders (active, order_date, due_date, return_date, book_id, student_id)
VALUES (false, '2026-01-10', '2026-01-24', '2026-01-22', 1, 1),
       (false, '2026-01-11', '2026-01-25', '2026-01-24', 2, 1),
       (true, '2026-03-01', '2026-03-15', NULL, 3, 1),
       (false, '2026-02-05', '2026-02-19', '2026-02-18', 4, 2),
       (true, '2026-03-10', '2026-03-24', NULL, 5, 2),
       (false, '2026-01-17', '2026-01-31', '2026-01-29', 6, 3),
       (false, '2026-02-11', '2026-02-25', '2026-02-23', 7, 3),
       (true, '2026-03-12', '2026-03-26', NULL, 8, 3),
       (false, '2026-01-20', '2026-02-03', '2026-01-31', 9, 4),
       (false, '2026-02-08', '2026-02-22', '2026-02-21', 10, 4),
       (true, '2026-03-02', '2026-03-16', NULL, 11, 4),
       (false, '2026-01-15', '2026-01-29', '2026-01-28', 12, 5),
       (false, '2026-02-10', '2026-02-24', '2026-02-20', 13, 5),
       (true, '2026-03-05', '2026-03-19', NULL, 14, 5),
       (false, '2026-01-13', '2026-01-27', '2026-01-26', 15, 6),
       (false, '2026-02-14', '2026-02-28', '2026-02-25', 16, 6),
       (true, '2026-03-11', '2026-03-25', NULL, 17, 6),
       (false, '2026-01-09', '2026-01-23', '2026-01-20', 18, 7),
       (false, '2026-02-12', '2026-02-26', '2026-02-24', 19, 7),
       (true, '2026-03-06', '2026-03-20', NULL, 20, 7),
       (false, '2026-01-18', '2026-02-01', '2026-01-30', 21, 8),
       (false, '2026-02-02', '2026-02-16', '2026-02-14', 22, 8),
       (true, '2026-03-07', '2026-03-21', NULL, 23, 8),
       (false, '2026-01-21', '2026-02-04', '2026-02-01', 24, 9),
       (false, '2026-02-06', '2026-02-20', '2026-02-18', 25, 9),
       (true, '2026-03-04', '2026-03-18', NULL, 26, 9),
       (false, '2026-01-14', '2026-01-28', '2026-01-26', 27, 10),
       (true, '2026-03-03', '2026-03-17', NULL, 28, 10),
       (false, '2026-02-03', '2026-02-17', '2026-02-16', 1, 11),
       (true, '2026-03-08', '2026-03-22', NULL, 4, 11),
       (false, '2026-02-04', '2026-02-18', '2026-02-15', 7, 12),
       (true, '2026-03-09', '2026-03-23', NULL, 10, 12),
       (false, '2026-02-07', '2026-02-21', '2026-02-19', 13, 13),
       (true, '2026-03-13', '2026-03-27', NULL, 16, 13),
       (false, '2026-02-09', '2026-02-23', '2026-02-22', 19, 14),
       (true, '2026-03-14', '2026-03-28', NULL, 22, 14),
       (false, '2026-02-13', '2026-02-27', '2026-02-26', 25, 15),
       (true, '2026-03-15', '2026-03-29', NULL, 28, 15),
       (false, '2026-02-15', '2026-03-01', '2026-02-28', 2, 16),
       (true, '2026-03-16', '2026-03-30', NULL, 5, 16),
       (false, '2026-02-16', '2026-03-02', '2026-03-01', 8, 17),
       (true, '2026-03-17', '2026-03-31', NULL, 11, 17),
       (false, '2026-02-17', '2026-03-03', '2026-03-02', 14, 18),
       (true, '2026-03-18', '2026-04-01', NULL, 17, 18),
       (false, '2026-02-18', '2026-03-04', '2026-03-03', 20, 19),
       (true, '2026-03-19', '2026-04-02', NULL, 23, 19),
       (false, '2026-02-19', '2026-03-05', '2026-03-04', 26, 20),
       (true, '2026-03-20', '2026-04-03', NULL, 27, 20);

SELECT s.name       AS student,
       g.code       AS group_code,
       f.short_name AS faculty,
       a.name       AS author,
       COUNT(*)     AS books_count
FROM students AS s
         JOIN student_groups AS g ON s.group_id = g.id
         JOIN faculties AS f ON g.faculty_id = f.id
         JOIN orders AS o ON s.id = o.student_id
         JOIN books AS b ON o.book_id = b.id
         JOIN authors AS a ON b.author_id = a.id
GROUP BY s.name, g.code, f.short_name, a.name
HAVING COUNT(*) >= 2;

CREATE VIEW show_students_with_2_or_more_books
AS
SELECT s.name       AS student,
       g.code       AS group_code,
       f.short_name AS faculty,
       a.name       AS author,
       COUNT(*)     AS books_count
FROM students AS s
         JOIN student_groups AS g ON s.group_id = g.id
         JOIN faculties AS f ON g.faculty_id = f.id
         JOIN orders AS o ON s.id = o.student_id
         JOIN books AS b ON o.book_id = b.id
         JOIN authors AS a ON b.author_id = a.id
GROUP BY s.name, g.code, f.short_name, a.name
HAVING COUNT(*) >= 2;

CREATE MATERIALIZED VIEW mat_students_with_2_or_more_books
AS
SELECT s.name       AS student,
       g.code       AS group_code,
       f.short_name AS faculty,
       a.name       AS author,
       COUNT(*)     AS books_count
FROM students AS s
         JOIN student_groups AS g ON s.group_id = g.id
         JOIN faculties AS f ON g.faculty_id = f.id
         JOIN orders AS o ON s.id = o.student_id
         JOIN books AS b ON o.book_id = b.id
         JOIN authors AS a ON b.author_id = a.id
GROUP BY s.name, g.code, f.short_name, a.name
HAVING COUNT(*) >= 2;

SELECT *
FROM show_students_with_2_or_more_books;
