/* Column for the schema person */

ALTER TABLE person.permissions
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE person.permissions
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE person.roles
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE person.roles
    ADD COLUMN modified_date TIMESTAMP;

ALTER TABLE person.users
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE person.users
    ADD COLUMN modified_date TIMESTAMP;