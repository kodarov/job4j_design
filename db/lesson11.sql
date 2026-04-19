-- Добавьте процедуру и функцию, которая будет удалять записи.
CREATE DATABASE lesson11_example;

CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 8, 115),
       ('product_2', 'producer_2', 3, 50),
       ('product_3', 'producer_3', 8, 115),
       ('product_4', 'producer_4', 3, 50),
       ('product_5', 'producer_5', 8, 115),
       ('product_6', 'producer_6', 3, 50),
       ('product_7', 'producer_7', 0, 50),
       ('product_8', 'producer_8', 0, 50);

CREATE
    OR REPLACE PROCEDURE delete_data(i_count INTEGER)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    DELETE
    FROM products
    WHERE count = i_count;
END
$$;

CALL delete_data(0);

CREATE
    OR REPLACE FUNCTION delete_data_overprice(f_producer VARCHAR, f_price INTEGER)
    RETURNS VOID
    LANGUAGE 'plpgsql' AS

$$
BEGIN
    DELETE
    FROM products p
    WHERE p.producer = f_producer
      AND p.price > f_price;
END;
$$;

SELECT delete_data_overprice('producer_7', '24');