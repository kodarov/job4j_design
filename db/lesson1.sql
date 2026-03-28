CREATE DATABASE test_dep;

CREATE TABLE avatars
(
    id  SERIAL PRIMARY KEY,
    url TEXT
);

CREATE TABLE space
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255)
);

CREATE TABLE sec_cards
(
    id          SERIAL PRIMARY KEY,
    number      INTEGER,
    date_expire TIMESTAMP
);

CREATE TABLE employees
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    role_id    INTEGER REFERENCES roles (id),
    avatar_id  INTEGER REFERENCES avatars (id)
);

CREATE TABLE xref_employees_2_sec_cards
(
    id           SERIAL PRIMARY KEY,
    sec_cards_id INTEGER REFERENCES sec_cards (id) UNIQUE,
    employees_id INTEGER REFERENCES employees (id) UNIQUE
);

CREATE TABLE xref_employees_2_space
(
    id           SERIAL PRIMARY KEY,
    space_id     INTEGER REFERENCES space (id),
    employees_id INTEGER REFERENCES employees (id)
);









