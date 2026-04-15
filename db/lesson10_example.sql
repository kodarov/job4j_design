CREATE DATABASE lesson10_example;

CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);


CREATE
    OR REPLACE FUNCTION discount()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price - price * 0.2
    WHERE count <= 5
      AND id = new.id;
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';

-- триггер row уровня
CREATE TRIGGER discount_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE discount();

INSERT INTO products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

SELECT *
FROM products;

ALTER TABLE products
    DISABLE TRIGGER discount_trigger;
DROP TRIGGER discount_trigger ON products;


CREATE
    OR REPLACE FUNCTION tax()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price - price * 0.2
    WHERE id = (SELECT id FROM inserted)
      AND count <= 5;
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

-- триггер statement уровня
CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS
        inserted
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax();

