--  db schema

CREATE SCHEMA IF NOT EXISTS "base";

CREATE TABLE IF NOT EXISTS users (
    id          BIGINT PRIMARY KEY GENERATED always AS IDENTITY,
    user_Id     UUID NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(100) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    active      BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS roles (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id     BIGINT NOT NULL REFERENCES users(id),
    role_id     BIGINT NOT NULL REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (name)
SELECT 'ROLE_USER'
WHERE
NOT EXISTS (SELECT * FROM roles WHERE name = 'ROLE_USER');

INSERT INTO roles (name)
SELECT 'ROLE_ADMIN'
WHERE
NOT EXISTS (SELECT * FROM roles WHERE name = 'ROLE_ADMIN');