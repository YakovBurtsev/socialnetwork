DROP TABLE IF EXISTS requests;

CREATE TABLE requests
(
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  from_user_id BIGINT NOT NULL,
  to_user_id   BIGINT NOT NULL
);




