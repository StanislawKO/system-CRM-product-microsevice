CREATE SEQUENCE discount_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS discount
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('discount_sequence'),
    amount     SMALLINT  NOT NULL CHECK (amount >= 0 AND amount <= 100),
    start_time TIMESTAMP NOT NULL CHECK (start_time <= end_time),
    end_time   TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL

);

CREATE SEQUENCE product_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS product
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('product_sequence'),
    summary     VARCHAR(100)  NOT NULL,
    description VARCHAR(100)  NOT NULL,
    price       NUMERIC(5, 2) NOT NULL,
    duration    SMALLINT      NOT NULL,
    discount_id BIGINT        NOT NULL,
    active      BOOLEAN       NOT NULL,
    created_at  TIMESTAMP     NOT NULL,
    updated_at  TIMESTAMP     NOT NULL,
    FOREIGN KEY (discount_id) REFERENCES discount (id)
);


