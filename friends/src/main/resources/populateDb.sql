DELETE FROM friends;

INSERT INTO friends (user_id, friend_id) VALUES
  (1, 2),
  (1, 3),
  (2, 1),
  (3, 1);

INSERT INTO requests (from_user_id, to_user_id) VALUES
  (1, 4),
  (1, 5),
  (6, 1);