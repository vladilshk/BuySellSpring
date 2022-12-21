DELETE
FROM favorites_product_list;
DELETE
FROM favorites;
DELETE
FROM products;
DELETE
FROM user_role;
DELETE
FROM users;

INSERT users(id, active, email, name, nuber, password)
VALUES (1, 1, '1@mail.ru', 'user1', 12345, '$2a$08$zoC2m.KUUOp6KZ05coVGcu.Dy9w4oOSbwUHh8CiXwHWeI/EwgxvLa'),
       (2, 1, '2@mail.ru', 'user2', 22345, '$2a$08$zoC2m.KUUOp6KZ05coVGcu.Dy9w4oOSbwUHh8CiXwHWeI/EwgxvLa'),
       (3, 1, '3@mail.ru', 'user3', 32345, '$2a$08$zoC2m.KUUOp6KZ05coVGcu.Dy9w4oOSbwUHh8CiXwHWeI/EwgxvLa');

INSERT user_role(user_id, roles)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (1, 'ROLE_USER');

INSERT products(id, city, date, description, price, title, views, user_id)
VALUES (1, 'Moscow', ' ', ' ', 4000, 'product1', 3, 1),
       (2, 'Moscow', ' ', ' ', 3000, 'product2', 4, 1),
       (3, 'Moscow', ' ', ' ', 2000, 'product3', 5, 2),
       (4, 'Moscow', ' ', ' ', 1000, 'product4', 6, 2);

INSERT favorites(user_id)
VALUES (1),
       (2),
       (3);
INSERT favorites_product_list(favourites_user_id, product_list_id)
VALUES (1, 2),
       (1, 4),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4);
