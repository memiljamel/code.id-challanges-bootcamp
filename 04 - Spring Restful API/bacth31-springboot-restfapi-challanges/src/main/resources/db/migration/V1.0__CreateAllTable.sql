CREATE SCHEMA oe;

CREATE TABLE oe.shippers
(
    shipper_id   SERIAL PRIMARY KEY,
    company_name VARCHAR(40) NOT NULL,
    phone        VARCHAR(24)
);

CREATE TABLE oe.employees
(
    employee_id       SERIAL PRIMARY KEY,
    last_name         VARCHAR(20),
    first_name        VARCHAR(10) NOT NULL,
    title             VARCHAR(30),
    title_of_courtesy VARCHAR(25),
    birth_date        DATE,
    hire_date         DATE        NOT NULL,
    address           VARCHAR(60),
    city              VARCHAR(15),
    region            VARCHAR(15),
    postal_code       VARCHAR(10),
    country           VARCHAR(15),
    home_phone        VARCHAR(24),
    extension         VARCHAR(4),
    photo             BYTEA,
    notes             TEXT,
    reports_to        SMALLINT,
    photo_path        VARCHAR(255)
);

CREATE TABLE oe.customers
(
    customer_id   VARCHAR(5) PRIMARY KEY,
    company_name  VARCHAR(40) NOT NULL,
    contact_name  VARCHAR(30),
    contact_title VARCHAR(30),
    address       VARCHAR(60),
    city          VARCHAR(15),
    region        VARCHAR(15),
    postal_code   VARCHAR(10),
    country       VARCHAR(15),
    phone         VARCHAR(24),
    fax           VARCHAR(24)
);

CREATE TABLE oe.orders
(
    order_id         SERIAL PRIMARY KEY,
    customer_id      VARCHAR(5),
    employee_id      SMALLINT,
    order_date       DATE NOT NULL DEFAULT CURRENT_DATE,
    required_date    DATE,
    shipped_date     DATE,
    ship_via         SMALLINT,
    freight          REAL,
    ship_name        VARCHAR(40),
    ship_address     VARCHAR(60),
    ship_city        VARCHAR(15),
    ship_region      VARCHAR(15),
    ship_postal_code VARCHAR(10),
    ship_country     VARCHAR(15),
    FOREIGN KEY (employee_id) REFERENCES oe.employees (employee_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES oe.customers (customer_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (ship_via) REFERENCES oe.shippers (shipper_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.suppliers
(
    supplier_id   SERIAL PRIMARY KEY,
    company_name  VARCHAR(40) NOT NULL,
    contact_name  VARCHAR(30),
    contact_title VARCHAR(30),
    address       VARCHAR(60),
    city          VARCHAR(15),
    region        VARCHAR(15),
    postal_code   VARCHAR(10),
    country       VARCHAR(15),
    phone         VARCHAR(24),
    fax           VARCHAR(24),
    homepage      TEXT
);

CREATE TABLE oe.categories
(
    category_id   SERIAL PRIMARY KEY,
    category_name VARCHAR(15) NOT NULL,
    description   TEXT,
    picture       BYTEA
);

CREATE TABLE oe.products
(
    product_id        SERIAL PRIMARY KEY,
    product_name      VARCHAR(40) NOT NULL,
    supplier_id       SMALLINT,
    category_id       SMALLINT,
    quantity_per_unit VARCHAR(20),
    unit_price        REAL CHECK (unit_price > 0),
    units_in_stock    SMALLINT,
    units_in_order    SMALLINT,
    reorder_level     SMALLINT,
    discontinued      INTEGER     NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES oe.suppliers (supplier_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES oe.categories (category_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE oe.order_details
(
    order_id   SMALLINT,
    product_id SMALLINT,
    unit_price REAL CHECK (unit_price > 0),
    quantity   SMALLINT CHECK (quantity > 0),
    discount   REAL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES oe.orders (order_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES oe.products (product_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);