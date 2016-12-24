DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE users_seq RESTART WITH 100000;

INSERT INTO users (name, surname, email, password) VALUES
  ('Ivan', 'Ivanov', 'ivan@yandex.ru', '$2a$10$wzN6jaWIb.kYAcJ1gSdM5.RNSGgJrOeNn7TLub/Q6/WJGL9/JQWYC'),
  ('Petr', 'Petrov', 'petr@gmail.com', '$2a$10$1cGWmN5B1n51oF6aRLp/WeRtXui8Y50FkVPYwgkkL8MZUDofmdcO6'),
  ('Vasiliy', 'Ivanov', 'vasiliy@gmail.com', '$2a$10$a8tEjkYiIeYkfY.6j37sR.EvjLeWqiTn90j71l3euImlstYurqj9.'),
  ('Vasiliy', 'Novikov', 'novikov@gmail.com', '$2a$10$XquO/lLJpie38qUQL9Fc5uAJ8.gVJIC.VJOZBRgSixu2wegoSlkHG');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003);

