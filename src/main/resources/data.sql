insert into role(id, name) VALUES (1, 'ROLE_USER');
insert into role(id, name) VALUES (2, 'ROLE_ADMIN');

insert into user(id, username, email, password, enable) VALUES (1, 'user', 'user@user', '$2a$12$VqQseRhCFN0K/hBM5IrIa.BALT76s8nK.mQktWvbJOsYpNsDHZmhe', true);
insert into user(id, username, email, password, enable) VALUES (2, 'admin', 'admin@admin', '$2a$12$t6rDv0RBeLs.Hd1u/5j0b.6BzwS.LuWSLyie8azuow7MdzBF8r74S', true);

insert into primary_account(id, account_number, account_balance, currency, user_id) VALUES (1, 12533456, 1245, 'RUR', 1);
insert into saving_account(id, account_number, account_balance, user_id) VALUES (1, 12533456, 2623.45, 1);

insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (1, to_date('24-09-2017 22:01:02', 'dd-MM-yyyy hh:mm:ss'), 'ok', 230, 230, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (2, to_date('01-11-2017 14:24:11', 'dd-MM-yyyy hh:mm:ss'), 'ok', 230, 460, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (3, to_date('24-11-2017 21:30:02', 'dd-MM-yyyy hh:mm:ss'), 'ok', -230, 230, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (4, to_date('02-12-2017 02:01:02', 'dd-MM-yyyy hh:mm:ss'), 'ok', -230, 0, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (5, to_date('11-12-2017 18:45:45', 'dd-MM-yyyy hh:mm:ss'), 'ok', 200, 200, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (6, to_date('11-01-2018 22:01:02', 'dd-MM-yyyy hh:mm:ss'), 'ok', 80, 280, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (7, to_date('22-01-2018 23:11:02', 'dd-MM-yyyy hh:mm:ss'), 'ok', -280, 0, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (8, to_date('03-02-2018 20:03:44', 'dd-MM-yyyy hh:mm:ss'), 'ok', 230, 230, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (9, to_date('04-02-2018 12:30:11', 'dd-MM-yyyy hh:mm:ss'), 'ok', 20, 20, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (10, to_date('04-02-2018 23:19:40', 'dd-MM-yyyy hh:mm:ss'), 'ok', 20, 40, 1);
insert into primary_transaction(id, date, status, amount, available_balance, account_id)
values (11, to_date('04-02-2018 08:55:14', 'dd-MM-yyyy hh:mm:ss'), 'ok', 100, 140, 1);