create table users
(
    id          SERIAL PRIMARY KEY,
    first_name   VARCHAR,
    last_name    VARCHAR,
    phone_number VARCHAR,
    password VARCHAR
);

ALTER TABLE users ADD email VARCHAR;
ALTER TABLE users ADD img VARCHAR;

create table session_info
(
    id  SERIAL PRIMARY KEY,
    user_id SERIAL NOT NULL,
    time BIGINT,
    ip_address  VARCHAR
);