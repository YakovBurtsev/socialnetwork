DELETE FROM users;

ALTER SEQUENCE users_seq RESTART WITH 100000;

INSERT INTO users (name, surname, email, password) VALUES
  ('Ivan', 'Ivanov', 'ivan@yandex.ru', 'ivan123'),
  ('Petr', 'Petrov', 'petr@gmail.com', 'petr123'),
  ('Vasiliy', 'Vasiliev', 'vasiliy@gmail.com', 'vasiliy123'),
  ('Vitaliy', 'Novikov', 'novikov@gmail.com', 'novikov123'),
  ('Olga', 'Orlova', 'orlova@mail.ru', 'orlova123');