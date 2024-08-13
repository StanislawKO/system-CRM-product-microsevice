-- Insert data into the discount table
INSERT INTO discount (amount, start_time, end_time, created_at, updated_at)
VALUES (10, '2024-07-01 00:00:00', '2024-07-31 23:59:59', now(), now()),
       (15, '2024-08-01 00:00:00', '2024-08-31 23:59:59', now(), now()),
       (20, '2024-09-01 00:00:00', '2024-09-30 23:59:59', now(), now()),
       (25, '2024-10-01 00:00:00', '2024-10-31 23:59:59', now(), now());

-- Insert data into the product table
INSERT INTO product (summary, description, price, duration, discount_id, active, created_at, updated_at)
VALUES ('product1_summary', 'product1_description', 19.7, 1, 1, true, now(), now()),
       ('product2_summary', 'product2_description', 27.6, 30, 2, true, now(), now()),
       ('product3_summary', 'product3_description', 33.4, 60, 3, false, now(), now()),
       ('product4_summary', 'product4_description', 48.7, 90, 4, true, now(), now());

