-- Insert data into the price table
INSERT INTO price (value, currency, created_at, updated_at)
VALUES
    (19.99, 'USD', now(), now()),
    (29.99, 'EUR', now(), now()),
    (39.99, 'GBP', now(), now()),
    (49.99, 'JPY', now(), now());

-- Insert data into the discount table
INSERT INTO discount (value, from_at, until, created_at, updated_at)
VALUES
    (10, '2024-07-01 00:00:00', '2024-07-31 23:59:59', now(), now()),
    (15, '2024-08-01 00:00:00', '2024-08-31 23:59:59', now(), now()),
    (20, '2024-09-01 00:00:00', '2024-09-30 23:59:59', now(), now()),
    (25, '2024-10-01 00:00:00', '2024-10-31 23:59:59', now(), now());

-- Insert data into the duration table
INSERT INTO duration (in_days, created_at, updated_at)
VALUES
    (1, now(), now()),
    (30, now(), now()),
    (60, now(), now()),
    (90, now(), now());

-- Insert data into the product table
INSERT INTO product (summary_key, description_key, price_id, discount_id, duration_id, active, created_at, updated_at)
VALUES
    ('product1_summary', 'product1_description', 1, 1, 1, true, now(), now()),
    ('product2_summary', 'product2_description', 2, 2, 2, true, now(), now()),
    ('product3_summary', 'product3_description', 3, 3, 3, false, now(), now()),
    ('product4_summary', 'product4_description', 4, 4, 4, true, now(), now());

