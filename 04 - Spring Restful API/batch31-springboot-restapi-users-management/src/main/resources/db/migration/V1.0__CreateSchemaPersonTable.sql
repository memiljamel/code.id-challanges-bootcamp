CREATE SCHEMA person;

CREATE SEQUENCE person.users_user_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE person.roles_role_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE person.permissions_permission_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE person.users
(
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(125) NOT NULL
);

CREATE TABLE person.roles
(
    role_id   SERIAL PRIMARY KEY,
    role_name VARCHAR(200) NOT NULL,
    user_id  INTEGER      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES person.users (user_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE person.permissions
(
    permission_id   SERIAL PRIMARY KEY,
    permission_type VARCHAR(25) CHECK (permission_type IN ('READ', 'READ_WRITE')),
    role_id         INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES person.roles (role_id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
