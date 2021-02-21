DROP TABLE IF EXISTS Films, Clients, Orders;
DROP TYPE IF EXISTS medium_type;
DROP DATABASE IF EXISTS java_prak;

CREATE DATABASE java_prak;
\connect java_prak

CREATE TYPE medium_type AS ENUM ('cassette', 'disc');

CREATE TABLE Films (
  film_id SERIAL,
  film_name text,
  producer text,
  release_year integer,
  cassette_total_number integer DEFAULT 0 CHECK (cassette_total_number >= 0),
  disc_total_number integer DEFAULT 0 CHECK (disc_total_number >= 0),
  cassette_available_number integer DEFAULT 0 CHECK (cassette_available_number >= 0),
  disc_available_number integer DEFAULT 0 CHECK (disc_available_number >= 0),
  cassette_price integer DEFAULT NULL CHECK (cassette_price >= 0 OR cassette_price IS NULL),
  disk_price integer DEFAULT NULL CHECK (disk_price >= 0 OR disk_price IS NULL),
  film_is_removed boolean DEFAULT false,
  PRIMARY KEY (film_id)
);


CREATE TABLE Clients (
  client_id SERIAL,
  client_name text,
  phone text,
  client_is_removed boolean DEFAULT false,
  PRIMARY KEY (client_id)
);


CREATE TABLE Orders (
  order_id SERIAL,
  client_id integer NOT NULL REFERENCES Clients ON DELETE RESTRICT,
  film_id integer NOT NULL REFERENCES Films ON DELETE RESTRICT,
  medium medium_type NOT NULL,
  price integer NOT NULL CHECK (price >= 0),
  film_issue_date date NOT NULL,
  film_return_date date,
  PRIMARY KEY (order_id)
);


