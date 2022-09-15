create table users
(
    id          SERIAL PRIMARY KEY,
    first_name   VARCHAR,
    last_name    VARCHAR,
    phone_number VARCHAR,
    password VARCHAR,
    email VARCHAR,
    img VARCHAR
);

create table session_info
(
    id  SERIAL PRIMARY KEY,
    user_id SERIAL NOT NULL,
    time_milisec BIGINT,
    ip  VARCHAR,
    time VARCHAR,
    date VARCHAR
);

create table user_images
(
    id  SERIAL PRIMARY KEY,
    original_name VARCHAR,
    name  VARCHAR,
    size BIGINT,
    mime VARCHAR
);