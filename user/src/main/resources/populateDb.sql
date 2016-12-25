DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE users_seq RESTART WITH 1;

INSERT INTO users (name, surname, birthday, sex, city, email, password) VALUES
  ('Иван', 'Иванов', '1990-01-08', 'MALE', 'Санкт-Петербург','ivan@yandex.ru',
   '$2a$10$wzN6jaWIb.kYAcJ1gSdM5.RNSGgJrOeNn7TLub/Q6/WJGL9/JQWYC'),

  ('Петр', 'Петров', '1992-05-14', 'MALE', 'Москва', 'petr@gmail.com',
   '$2a$10$1cGWmN5B1n51oF6aRLp/WeRtXui8Y50FkVPYwgkkL8MZUDofmdcO6'),

  ('Василий', 'Иванов', '1991-08-11', 'MALE', 'Санкт-Петербург', 'vasiliy@gmail.com',
   '$2a$10$a8tEjkYiIeYkfY.6j37sR.EvjLeWqiTn90j71l3euImlstYurqj9.'),

  ('Василий', 'Новиков', '1995-10-09', 'MALE', 'Краснодар', 'novikov@gmail.com',
   '$2a$10$XquO/lLJpie38qUQL9Fc5uAJ8.gVJIC.VJOZBRgSixu2wegoSlkHG'),

  ('Aнна', 'Федорова', '1992-02-15', 'FEMALE', 'Москва',  'anna@gmail.com',
   '$2a$10$nz9JP9YXi2O.uKzipRH.R.V8OOdGJlolrAk6WZcJMSXh2g4KwASz2'),

  ('Татьяна', 'Михайлова', '1990-10-22', 'FEMALE', 'Санкт-Петербург','tatyana@gmail.com',
   '$2a$10$wtyuOqsnS7ZoWwhUyhj4ouC/5rwa1FmD96iDMuTJ.PAPQvjIdIgVa');


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_USER', 2),
  ('ROLE_USER', 3),
  ('ROLE_USER', 4),
  ('ROLE_USER', 5),
  ('ROLE_USER', 6);

