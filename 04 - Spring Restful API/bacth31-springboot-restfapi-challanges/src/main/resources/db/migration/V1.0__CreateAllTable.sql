CREATE SCHEMA oe;

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
    address       VARCHAR(60),
    city          VARCHAR(15),
    region        VARCHAR(15),
    postal_code   VARCHAR(10),
    country       VARCHAR(15),
    phone         VARCHAR(24),
    fax           VARCHAR(24),
    homepage      TEXT
);

CREATE TABLE oe.shippers
(
    shipper_id   SERIAL PRIMARY KEY,
    company_name VARCHAR(40) NOT NULL,
    phone        VARCHAR(24)
);
