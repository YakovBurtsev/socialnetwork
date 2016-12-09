DELETE FROM users;

ALTER SEQUENCE users_seq RESTART WITH 100000;

INSERT INTO users (name, surname, email, password)
VALUES ('Ivan', 'Ivanov', 'ivan@yandex.ru', 'ivan123');

INSERT INTO users (name, surname, email, password)
VALUES ('Petr', 'Petrov', 'petr@gmail.com', 'petr123');