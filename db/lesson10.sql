CREATE DATABASE lesson10_db;

CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

--  1) Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар
--  (нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд, а на запрос (statement уровень)

--функция для statement
CREATE
    OR REPLACE FUNCTION tax()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price + price * 0.13
    WHERE id IN (SELECT id FROM temp);
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';

-- триггер statement уровня
CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS temp
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax();

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 8, 115),
       ('product_2', 'producer_2', 3, 50);

-- 2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар
-- (нужно прибавить налог к цене товара). Здесь используем row уровень.

--функция для row
CREATE
    OR REPLACE FUNCTION posttax()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price + price * 0.2
    WHERE id = NEW.id;
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';

-- триггер row уровня
CREATE TRIGGER discount_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE posttax();

INSERT INTO products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115),
       ('product_4', 'producer_4', 3, 50);

-- 3) Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
-- будет заносить имя, цену и текущую дату в таблицу history_of_price.

--функция для row
CREATE
    OR REPLACE FUNCTION logging_price()
    RETURNS trigger AS
$$
BEGIN
    INSERT INTO history_of_price(name, price, date)
    VALUES (NEW.name, NEW.price, current_timestamp);
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';

-- триггер row уровня
CREATE TRIGGER history_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE logging_price();

INSERT INTO products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 8, 115),
       ('product_6', 'producer_6', 3, 50);

SELECT *
FROM history_of_price;


