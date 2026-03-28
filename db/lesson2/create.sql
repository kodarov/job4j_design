CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE permissions
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE xref_roles_2_permissions
(
    id             SERIAL PRIMARY KEY,
    roles_id       INT REFERENCES roles (id),
    permissions_id INT REFERENCES permissions (id)
);

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    roles_id   INT REFERENCES roles (id)
);

CREATE TABLE states
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE items
(
    id            SERIAL PRIMARY KEY,
    summary       VARCHAR(255),
    users_id      INT REFERENCES users (id),
    categories_id INT REFERENCES categories (id),
    states_id     INT REFERENCES states (id)
);

CREATE TABLE comments
(
    id       SERIAL PRIMARY KEY,
    content  TEXT,
    items_id INTEGER REFERENCES items (id)
);

CREATE TABLE attachments
(
    id        SERIAL PRIMARY KEY,
    url       TEXT,
    extension VARCHAR(255),
    items_id  INTEGER REFERENCES items (id)
);

