DELETE FROM requests;

INSERT INTO requests (from_user_id, to_user_id) VALUES
  (0, 1),
  (0, 2),
  (0, 3),
  (4, 0),
  (5, 0),
  (5, 6),
  (3, 4),
  (3, 5);
