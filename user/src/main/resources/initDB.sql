DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR NOT NULL,
  surname    VARCHAR NOT NULL,
  birthday   TIMESTAMP,
  sex        VARCHAR,
  city       VARCHAR,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL
);
