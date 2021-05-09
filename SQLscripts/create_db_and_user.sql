DROP OWNED BY web_server_user;
DROP USER IF EXISTS web_server_user;

DROP DATABASE IF EXISTS web_server_db;
CREATE DATABASE web_server_db;

CREATE USER web_server_user WITH ENCRYPTED PASSWORD '1';
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO web_server_user;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO web_server_user;

\connect web_server_db
SET ROLE web_server_user;
