create user web_server_user with encrypted password '1';
\connect web_server_db
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO web_server_user;
-- GRANT ALL PRIVILEGES, USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO web_server_user;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO web_server_user;

SET ROLE web_server_user;
SELECT SESSION_USER, CURRENT_USER;

-- insert into clients (client_id, is_client_removed, client_name, phone) values
-- ('114', false, 'test name1', 'some phone1');