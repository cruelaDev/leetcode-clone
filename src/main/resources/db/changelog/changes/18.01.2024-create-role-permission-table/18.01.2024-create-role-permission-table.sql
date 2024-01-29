create table role
(
    id   uuid primary key,
    name varchar not null unique
);

create table permission
(
    id   uuid primary key,
    name varchar not null unique
);
create table role_permission
(
    role_id       uuid references role,
    permission_id uuid references permission,
    primary key (role_id, permission_id)
)