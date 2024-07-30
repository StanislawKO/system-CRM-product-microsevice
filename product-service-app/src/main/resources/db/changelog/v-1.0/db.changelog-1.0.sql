CREATE SEQUENCE price_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS price
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('price_sequence'),
    value      NUMERIC(5, 2) NOT NULL,
    currency   VARCHAR(3)    NOT NULL CHECK (currency IN ('USD', 'EUR', 'GBP', 'JPY')),
    created_at TIMESTAMP     NOT NULL,
    updated_at TIMESTAMP     NOT NULL
);

CREATE SEQUENCE discount_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS discount
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('discount_sequence'),
    value      SMALLINT  NOT NULL CHECK (value >= 0 AND value <= 100),
    from_at    TIMESTAMP NOT NULL CHECK (from_at <= until),
    until      TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL

);

CREATE SEQUENCE duration_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS duration
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('duration_sequence'),
    in_days    SMALLINT  NOT NULL CHECK (in_days IN (1, 30, 60, 90)),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE SEQUENCE product_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS product
(
    id              BIGINT PRIMARY KEY DEFAULT nextval('product_sequence'),
    summary_key     VARCHAR(100) NOT NULL,
    description_key VARCHAR(100) NOT NULL,
    price_id        BIGINT       NOT NULL,
    discount_id     BIGINT       NOT NULL,
    duration_id     BIGINT       NOT NULL,
    active          BOOLEAN      NOT NULL,
    created_at      TIMESTAMP    NOT NULL,
    updated_at      TIMESTAMP    NOT NULL,
    UNIQUE (price_id, discount_id),
    FOREIGN KEY (price_id) REFERENCES price (id),
    FOREIGN KEY (discount_id) REFERENCES discount (id),
    FOREIGN KEY (duration_id) REFERENCES duration (id)
);


