DROP TABLE IF EXISTS friends;

CREATE TABLE friends
(
  user_id    BIGINT NOT NULL,
  friend_id  BIGINT NOT NULL
);

ALTER TABLE friends ADD PRIMARY KEY (user_id, friend_id);

