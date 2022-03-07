CREATE DATABASE pharmacy;
USE pharmacy;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(64) NOT NULL UNIQUE,
    email    VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    role     ENUM ('user', 'admin', 'pharmacist', 'doctor') DEFAULT 'user'
);

CREATE TABLE medicines
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(64)   NOT NULL,
    dose                INT           NOT NULL,
    is_receipt_required BOOL          NOT NULL DEFAULT FALSE,
    price               DECIMAL(8, 2) NOT NULL
);

CREATE TABLE receipts
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT NOT NULL,
    medicine_id     INT NOT NULL,
    expiration_date DATE,
    state           ENUM ('requested', 'approved', 'used') DEFAULT 'requested',
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (medicine_id) REFERENCES medicines (id)
);

use pharmacy;
drop table orders;

CREATE TABLE orders
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    date    DATE,
    user_id INT  NOT NULL,
    is_paid BOOL NOT NULL DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

# Связывает orders-medicines
CREATE TABLE order_items
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    order_id    INT NOT NULL,
    medicine_id INT NOT NULL,
    quantity    INT NOT NULL DEFAULT 1,
    FOREIGN KEY (order_id) REFERENCES orders (id) on delete cascade,
    FOREIGN KEY (medicine_id) REFERENCES medicines (id)
);

INSERT INTO users (name, email, password)
VALUES ('bob', 'bob@mail.ru', 'bob'),
       ('tom', 'tom@mail.ru', 'tom'),
       ('bill', 'bill@mail.ru', 'bill');

INSERT INTO medicines (name, dose, is_receipt_required, price)
VALUES ('Ambroxol', 200, true, 10),
       ('Ibuprofen', 100, true, 20),
       ('Paracetamol', 500, true, 30),
       ('Aspirin', 600, false, 40),
       ('Melatonin', 3, false, 50);

INSERT INTO receipts (user_id, medicine_id, expiration_date, state)
VALUES (1, 1, '2022-04-01', 'approved'),
       (2, 1, '2022-04-01', 'approved');

INSERT INTO receipts (user_id, medicine_id, state)
VALUES (1, 2, 'requested'),
       (2, 2, 'requested'),
       (3, 2, 'requested');

INSERT INTO orders (date, user_id, is_paid)
VALUES ('2022-02-17', 1, TRUE);

INSERT INTO orders (user_id)
VALUES (2),
       (3),
       (1);

INSERT INTO order_items (order_id, medicine_id)
VALUES (1, 1),
       (1, 2),
       (1, 5),
       (2, 1),
       (2, 4),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 2),
       (4, 4);

# Покупатель
#-------------------------------------------------------------------------------------
# 1) Получить все заказы (1-ый человек)
SELECT o.data, m.name, m.dose, m.price, om.quantity, o.is_paid
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
WHERE user_id = 1;

# 2) Получить все лекарства в неоплаченных заказах и (1-ый человек)
SELECT o.data, m.name, m.dose, m.price, om.quantity
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
WHERE user_id = 1
  AND o.is_paid = FALSE;

# 3) Получить все не рецептурные лекарства в неоплаченных заказах (1-ый человек)
SELECT o.data, m.name, m.dose, m.price, om.quantity
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
WHERE user_id = 1
  AND o.is_paid = FALSE
  AND m.is_receipt_required = FALSE;

# 4) Получить все неоплаченные заказы и рецептурные лекарства в них (1-ый человек)
SELECT o.data, m.name, m.dose, m.price, om.quantity
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
WHERE user_id = 1
  AND o.is_paid = FALSE
  AND m.is_receipt_required = TRUE;

# 5) Получить все без рецептурные и рецептурные лекарства c рецептами в неоплаченных заказах (1-ый человек)
SELECT o.data, m.name, m.dose, m.price, om.quantity, o.is_paid, o.user_id
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
         JOIN receipts r ON m.id = r.medicine_id
WHERE o.user_id = 1
  AND o.is_paid = FALSE
  AND m.is_receipt_required = TRUE
  AND (r.state = 'approved' AND r.user_id = 1)
UNION ALL
SELECT o.data, m.name, m.dose, m.price, om.quantity, o.is_paid, o.user_id
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
WHERE o.user_id = 1
  AND o.is_paid = FALSE
  AND m.is_receipt_required = FALSE;

# 6) Все запросы на рецепты (1-ый человек)
SELECT *
FROM receipts r
WHERE r.user_id = 1;

# 7) Запрос на продление рецепта
UPDATE receipts
SET state           = 'requested',
    expiration_date = null
WHERE id = 2;

# 8) Покупка: продаем всю корзину (изменяем поле is_paid и поле date), но проверить чтобы были все рецепты
UPDATE orders o
SET o.is_paid = true,
    o.data    = NOW()
WHERE o.user_id = 1
  AND o.is_paid = FALSE;

# 9) Считаем количество лекарств, которые 1-ый покупатель собирается купить.
SELECT COUNT(oi.id)
FROM order_items oi
         JOIN orders o ON oi.order_id = o.id
         JOIN users u ON o.user_id = u.id
WHERE o.is_paid =  FALSE
AND u.id = 1;

# 10) Считаем количество лекарств, которые может купить 1-ый покупатель.
SELECT COUNT(a.name)
FROM
(SELECT m.name
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
         JOIN receipts r ON m.id = r.medicine_id
WHERE o.user_id = 1
  AND o.is_paid = FALSE
  AND m.is_receipt_required = TRUE
  AND (r.state = 'approved' AND r.user_id = 1)
UNION ALL
SELECT m.name
FROM orders o
         JOIN order_items om ON o.id = om.order_id
         JOIN medicines m ON om.medicine_id = m.id
WHERE o.user_id = 1
  AND o.is_paid = FALSE
  AND m.is_receipt_required = FALSE) AS a;

# Врач
#-------------------------------------------------------------------------------------
# 1) Все запросы на рецепты
SELECT *
FROM receipts r
WHERE r.state = 'requested'
  AND r.state;

# 2) Обновить один рецепт (1-ый человек)
UPDATE receipts
SET receipts.state           = 'approved',
    receipts.expiration_date = now()
WHERE receipts.user_id = 1
  AND receipts.id = 1;
























