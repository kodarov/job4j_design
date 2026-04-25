CREATE DATABASE lesson14_example;

CREATE TABLE people
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    phone      VARCHAR(50)
);

CREATE INDEX people_last_name ON people (last_name DESC);
ALTER INDEX people_last_name RENAME TO people_last_name_desc;
DROP INDEX people_last_name_desc;
