DELETE FROM users;

ALTER SEQUENCE users_seq RESTART WITH 100000;

INSERT INTO users (name, surname, email, password)
VALUES ('Ivan', 'Ivanov', 'ivan@yandex.ru', 'ivan123'),
  ('Petr', 'Petrov', 'petr@gmail.com', 'petr123'),
  ('Vasiliy', 'Ivanov', 'vasiliy@gmail.com', 'vasiliy123'),
  ('Vasiliy', 'Novikov', 'novikov@gmail.com', 'novikov123');