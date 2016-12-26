DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS friends;
DROP SEQUENCE IF EXISTS requests_seq;

CREATE SEQUENCE requests_seq START 1;

CREATE TABLE friends
(
  user_id   BIGINT NOT NULL,
  friend_id BIGINT NOT NULL
);

ALTER TABLE friends
  ADD PRIMARY KEY (user_id, friend_id);

CREATE TABLE requests (
  id           BIGINT PRIMARY KEY DEFAULT nextval('requests_seq'),
  from_user_id BIGINT NOT NULL,
  to_user_id   BIGINT NOT NULL
);

ALTER TABLE requests
  ADD CONSTRAINT from_to_unique UNIQUE (from_user_id, to_user_id)