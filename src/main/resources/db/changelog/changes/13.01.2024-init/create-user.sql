create table "user"(
    id uuid primary key ,
    name varchar not null ,
    surname varchar not null ,
    phone_number varchar not null unique,
    email varchar unique ,
    password varchar not null ,
    birth_date timestamp,
    created timestamp not null ,
    updated timestamp not null
);