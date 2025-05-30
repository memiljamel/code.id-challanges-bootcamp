CREATE SCHEMA person;

CREATE SEQUENCE person.users_user_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE person.users
(
    user_id        SERIAL PRIMARY KEY,
    user_name      VARCHAR(15)  NOT NULL,
    user_email     VARCHAR(80)  NOT NULL,
    user_password  VARCHAR(125) NOT NULL,
    user_handphone VARCHAR(15),
    created_on     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_email, user_handphone)
);