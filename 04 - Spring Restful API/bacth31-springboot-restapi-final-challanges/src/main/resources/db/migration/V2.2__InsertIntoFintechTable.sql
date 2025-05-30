/* Data for the table bank */
INSERT INTO fintech.bank (bank_code, bank_name) VALUES ('002', 'Bank Rakyat Indonesia');
INSERT INTO fintech.bank (bank_code, bank_name) VALUES ('008', 'Bank Mandiri');
INSERT INTO fintech.bank (bank_code, bank_name) VALUES ('009', 'Bank Negara Indonesia');
INSERT INTO fintech.bank (bank_code, bank_name) VALUES ('014', 'Bank Central Asia');

/* Data for the table fintechs */

INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('002', 'Bank Rakyat Indonesia', 'BRI', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('008', 'Bank Mandiri', 'MANDIRI', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('009', 'Bank Negara Indonesia', 'BNI', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('014', 'Bank Central Asia', 'BCA', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('022', 'Bank CIMB Niaga', 'CIMB', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('028', 'Bank OCBC NISP', 'OCBC', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('426', 'Bank Mega', 'MEGA', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('013', 'Bank Permata', 'PERMATA', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('011', 'Bank Danamon', 'DANAMON', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('016', 'Bank Maybank Indonesia', 'MAYBANK', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('019', 'Bank Panin', 'PANIN', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('023', 'Bank UOB Indonesia', 'UOB', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('200', 'Bank Tabungan Negara', 'BTN', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('045', 'Bank BTPN/Jago', 'BTPN/JAGO', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('213', 'Bank BJB', 'BJB', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('490', 'Bank Neo Commerce', 'NEO', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('501', 'Bank Digital BCA (blu)', 'BCA DIGITAL', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('503', 'Bank Jago', 'JAGO', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('506', 'Bank Seabank', 'BANK', 'BANK');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('890', 'Dana', 'DANA', 'E-Wallet');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('991', 'OVO', 'OVO', 'E-Wallet');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('992', 'LinkAja', 'LINKAJA', 'E-Wallet');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('993', 'GoPay', 'GOPAY', 'E-Wallet');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('994', 'ShopeePay', 'SHOPEEPAY', 'E-Wallet');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('995', 'Flip', 'FLIP', 'P-GateAway');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('998', 'Jenius (BTPN) Digital Banking', 'JENIUS', 'E-Wallet');
INSERT INTO fintech.fintechs (fint_code, fint_name, fint_short_name, fint_type) VALUES ('999', 'PT. Alfamart', 'ALFAMART', 'E-Wallet');

/* Data for the table accounts */

INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (100, 'FIN-ALFA', 20000000.00, 'IDR', '2025-04-28 09:47:22.728257', 150, '999');
INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (101, 'FIN-BCA', 10000000.00, 'IDR', '2025-04-28 09:47:22.728257', 151, '014');
INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (102, 'FIN-GOPAY', 5000000.00, 'IDR', '2025-04-28 09:47:22.728257', 152, '993');
INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (103, 'FIN-SHOPEE', 3000000.00, 'IDR', '2025-04-28 09:47:22.728257', 153, '994');
INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (105, 'KANG-GOPAY', 100000.00, 'IDR', '2025-04-28 09:47:22.728257', 300, '993');
INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (107, 'WIDI-SHOPEE', 500.00, 'IDR', '2025-04-28 09:47:22.728257', 303, '994');
INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (104, 'KANG-BCA', 4600000.00, 'IDR', '2025-04-28 09:47:22.728257', 300, '014');
INSERT INTO fintech.accounts (account_id, account_no, balance, currency, created_at, user_id, fint_code) VALUES (106, 'WIDI-BCA', 1900000.00, 'IDR', '2025-04-28 09:47:22.728257', 303, '014');

/* Data for the table transactions */

INSERT INTO fintech.transactions (trac_id, trac_no, from_account, to_account, trac_noref, debet, credit, trac_type, description, trac_date, status) VALUES (7, 'TR2005-01', 'KANG-BCA', 'WIDI-BCA', NULL, 0.00, 150000.00, 'TRANSFER', 'Transfer To Widi', '2025-04-28 10:49:05.573437', 'SUCCESS');
INSERT INTO fintech.transactions (trac_id, trac_no, from_account, to_account, trac_noref, debet, credit, trac_type, description, trac_date, status) VALUES (8, 'TR2005-02', 'KANG-BCA', 'WIDI-BCA', 'TR2005-01', 150000.00, 0.00, 'TRANSFER', 'Transfer From Kang', '2025-04-28 10:49:05.573437', 'SUCCESS');
INSERT INTO fintech.transactions (trac_id, trac_no, from_account, to_account, trac_noref, debet, credit, trac_type, description, trac_date, status) VALUES (9, '2025042800010', 'KANG-BCA', 'WIDI-BCA', NULL, 0.00, 250000.00, 'TRANSFER', 'Transfer uang ke Widi', '2025-04-28 11:06:35.178259', 'SUCCESS');
INSERT INTO fintech.transactions (trac_id, trac_no, from_account, to_account, trac_noref, debet, credit, trac_type, description, trac_date, status) VALUES (11, '2025042800012', 'KANG-BCA', 'WIDI-BCA', '2025042800010', 250000.00, 0.00, 'TRANSFER', 'Transfer uang ke Widi', '2025-04-28 11:06:35.178259', 'SUCCESS');
