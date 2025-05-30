CREATE SCHEMA oe;

CREATE SEQUENCE oe.categories_category_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE oe.suppliers_supplier_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE oe.shippers_shipper_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE oe.products_product_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE oe.orders_order_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE oe.carts_cart_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE oe.categories
(
    category_id   SERIAL PRIMARY KEY,
    category_name VARCHAR(15) NOT NULL,
    description   TEXT,
    picture       BYTEA
);

CREATE TABLE oe.suppliers
(
    supplier_id   SERIAL PRIMARY KEY,
    company_name  VARCHAR(40) NOT NULL,
    contact_name  VARCHAR(30),
    contact_title VARCHAR(30),
    phone         VARCHAR(24),
    fax           VARCHAR(24),
    homepage      TEXT,
    location_id   INTEGER,
    FOREIGN KEY (location_id) REFERENCES hr.locations (location_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.shippers
(
    shipper_id   SERIAL PRIMARY KEY,
    company_name VARCHAR(40) NOT NULL,
    phone        VARCHAR(24)
);

CREATE TABLE oe.products
(
    product_id        SERIAL PRIMARY KEY,
    product_name      VARCHAR(40) NOT NULL,
    supplier_id       SMALLINT,
    category_id       SMALLINT,
    quantity_per_unit VARCHAR(20),
    unit_price        NUMERIC(8, 2) CHECK (unit_price > 0),
    units_in_stock    SMALLINT,
    units_in_order    SMALLINT,
    reorder_level     SMALLINT,
    discontinued      INTEGER     NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES oe.suppliers (supplier_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES oe.categories (category_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.orders
(
    order_id       SERIAL PRIMARY KEY,
    order_date     DATE NOT NULL DEFAULT CURRENT_DATE,
    required_date  DATE,
    shipped_date   DATE,
    ship_via       SMALLINT,
    freight        REAL,
    ship_name      VARCHAR(40),
    location_id    INTEGER,
    order_no       VARCHAR(15),
    total_discount DECIMAL(5, 2),
    total_amount   DECIMAL(8, 2),
    payment_type   VARCHAR(15) CHECK (payment_type IN ('DEBIT', 'CREDIT', 'QRIS', 'TRANSFER')),
    card_no        VARCHAR(25),
    transac_no     VARCHAR(25),
    transac_date   DATE          DEFAULT CURRENT_DATE,
    ref_no         VARCHAR(25),
    user_id        INTEGER,
    bank_code      VARCHAR(4),
    employee_id    SMALLINT,
    FOREIGN KEY (ship_via) REFERENCES oe.shippers (shipper_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (location_id) REFERENCES hr.locations (location_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES person.users (user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (bank_code) REFERENCES fintech.bank (bank_code)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES hr.employees (employee_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.order_details
(
    order_id   SMALLINT,
    product_id SMALLINT,
    unit_price NUMERIC(8, 2) CHECK (unit_price > 0),
    quantity   SMALLINT CHECK (quantity > 0),
    discount   NUMERIC(5, 2),
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES oe.orders (order_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES oe.products (product_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.carts
(
    cart_id    SERIAL PRIMARY KEY,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    INTEGER UNIQUE,
    FOREIGN KEY (user_id) REFERENCES person.users (user_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.cart_items
(
    cart_id    INTEGER,
    product_id INTEGER,
    unit_price NUMERIC(8, 2) CHECK (unit_price > 0),
    quantity   SMALLINT CHECK (quantity > 0),
    discount   NUMERIC(5, 2),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES oe.carts (cart_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES oe.products (product_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
