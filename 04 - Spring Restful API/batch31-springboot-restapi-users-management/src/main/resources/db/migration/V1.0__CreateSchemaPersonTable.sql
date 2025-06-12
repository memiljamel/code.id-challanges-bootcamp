CREATE SCHEMA person;

CREATE SEQUENCE person.roles_role_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE person.permissions_permission_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE person.permissions
(
    permission_id   SERIAL PRIMARY KEY,
    permission_type VARCHAR(25) CHECK (permission_type IN ('READ', 'READ_WRITE'))
);

CREATE TABLE person.roles
(
    role_id       SERIAL PRIMARY KEY,
    role_name     VARCHAR(200) NOT NULL,
    permission_id INTEGER      NOT NULL,
    FOREIGN KEY (permission_id) REFERENCES person.permissions (permission_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE person.users
(
    username VARCHAR(100) PRIMARY KEY,
    password VARCHAR(125) NOT NULL,
    role_id  INTEGER      NOT NULL,
    FOREIGN KEY (role_id) REFERENCES person.roles (role_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
