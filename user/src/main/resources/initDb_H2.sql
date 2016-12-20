DROP TABLE IF EXISTS user_roles;
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

CREATE TABLE user_roles
(
  user_id BIGINT NOT NULL,
  role    VARCHAR NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);