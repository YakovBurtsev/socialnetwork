DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS users_seq;

CREATE SEQUENCE users_seq START WITH 100000;

CREATE TABLE users
(
  id       BIGINT DEFAULT users_seq.nextval PRIMARY KEY,
  name     VARCHAR NOT NULL,
  surname  VARCHAR NOT NULL,
  birthday TIMESTAMP,
  sex      VARCHAR,
  city     VARCHAR,
  email    VARCHAR NOT NULL UNIQUE,
  password VARCHAR NOT NULL
);
