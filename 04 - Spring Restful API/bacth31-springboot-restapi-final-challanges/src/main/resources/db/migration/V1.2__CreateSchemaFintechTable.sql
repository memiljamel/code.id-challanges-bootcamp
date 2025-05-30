CREATE SCHEMA fintech;

CREATE SEQUENCE fintech.accounts_account_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE fintech.transactions_trac_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE fintech.bank
(
    bank_code VARCHAR(4) PRIMARY KEY,
    bank_name VARCHAR(55) NOT NULL
);

CREATE TABLE fintech.fintechs
(
    fint_code       VARCHAR(4) PRIMARY KEY,
    fint_name       VARCHAR(125),
    fint_short_name VARCHAR(15),
    fint_type       VARCHAR(10) CHECK (fint_type IN ('BANK', 'FINTECH', 'E-Wallet', 'P-GateAway'))
);

CREATE TABLE fintech.accounts
(
    account_id BIGSERIAL PRIMARY KEY,
    account_no VARCHAR(30) UNIQUE,
    balance    DECIMAL(15, 2),
    currency   VARCHAR(5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    INTEGER,
    fint_code  VARCHAR(4),
    FOREIGN KEY (user_id) REFERENCES person.users (user_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fint_code) REFERENCES fintech.fintechs (fint_code)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE fintech.transactions
(
    trac_id      BIGSERIAL PRIMARY KEY,
    trac_no      VARCHAR(25) UNIQUE,
    from_account VARCHAR(30),
    to_account   VARCHAR(30),
    trac_noref   VARCHAR(25),
    debet        DECIMAL(15, 2),
    credit       DECIMAL(15, 2),
    trac_type    VARCHAR(15) CHECK (trac_type IN ('TRANSFER', 'DEPOSIT', 'QRIS')),
    description  VARCHAR(35),
    trac_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status       VARCHAR(20) CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED', 'CANCELLED')),
    FOREIGN KEY (from_account) REFERENCES fintech.accounts (account_no)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (to_account) REFERENCES fintech.accounts (account_no)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (trac_noref) REFERENCES fintech.transactions (trac_no)
        ON UPDATE CASCADE ON DELETE CASCADE
);