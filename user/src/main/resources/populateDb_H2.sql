DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE users_seq RESTART WITH 100000;

INSERT INTO users (name, surname, email, password) VALUES
  ('Ivan', 'Ivanov', 'ivan@yandex.ru', 'ivan123'),
  ('Petr', 'Petrov', 'petr@gmail.com', 'petr123'),
  ('Vasiliy', 'Ivanov', 'vasiliy@gmail.com', 'vasiliy123'),
  ('Vasiliy', 'Novikov', 'novikov@gmail.com', 'novikov123'),
  ('Admin', 'Admin', 'admin@gmail.com', 'admin123');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_ADMIN', 100004),
  ('ROLE_USER', 100004);
