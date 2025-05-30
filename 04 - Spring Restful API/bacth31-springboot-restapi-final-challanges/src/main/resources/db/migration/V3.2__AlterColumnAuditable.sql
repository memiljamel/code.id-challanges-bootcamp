/* Column for the schema hr */

ALTER TABLE hr.regions
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE hr.regions
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE hr.countries
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE hr.countries
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE hr.locations
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE hr.locations
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE hr.departments
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE hr.departments
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE hr.jobs
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE hr.jobs
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE hr.employees
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE hr.employees
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE hr.dependents
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE hr.dependents
    ADD COLUMN modified_date TIMESTAMP;

/* Column for the schema person */

ALTER TABLE person.users
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE person.users
    ADD COLUMN modified_date TIMESTAMP;

/* Column for the schema fintech */

ALTER TABLE fintech.bank
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE fintech.bank
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE fintech.fintechs
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE fintech.fintechs
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE fintech.accounts
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE fintech.accounts
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE fintech.transactions
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE fintech.transactions
    ADD COLUMN modified_date TIMESTAMP;

/* Column for the schema oe */

ALTER TABLE oe.shippers
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.shippers
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.orders
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.orders
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.suppliers
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.suppliers
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.categories
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.categories
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.products
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.products
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.order_details
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.order_details
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.carts
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.carts
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.cart_items
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.cart_items
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE oe.product_images
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oe.product_images
    ADD COLUMN modified_date TIMESTAMP;
