insert into role(id, name) VALUES (1, 'ROLE_USER');
insert into role(id, name) VALUES (2, 'ROLE_ADMIN');

insert into user(id, username, email, password, enable) VALUES (1, 'user', 'user@user', '$2a$12$VqQseRhCFN0K/hBM5IrIa.BALT76s8nK.mQktWvbJOsYpNsDHZmhe', true);

insert into primary_account(id, account_number, account_balance, currency, user_id) VALUES (1, 12533456, 1245, 'RUR', 1);
insert into saving_account(id, account_number, account_balance, user_id) VALUES (1, 12533456, 2623.45, 1);

insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (1, to_date('24-09-2017', 'dd-MM-yyyy'), 'ok', 230, 230, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (2, to_date('01-11-2017', 'dd-MM-yyyy'), 'ok', 230, 460, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (3, to_date('24-11-2017', 'dd-MM-yyyy'), 'ok', -230, 230, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (4, to_date('02-12-2017', 'dd-MM-yyyy'), 'ok', -230, 0, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (5, to_date('11-12-2017', 'dd-MM-yyyy'), 'ok', 200, 200, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (6, to_date('11-01-2018', 'dd-MM-yyyy'), 'ok', 80, 280, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (7, to_date('22-01-2018', 'dd-MM-yyyy'), 'ok', -280, 0, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (8, to_date('03-02-2018', 'dd-MM-yyyy'), 'ok', 230, 230, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (9, to_date('04-02-2018', 'dd-MM-yyyy'), 'ok', 20, 20, 1);