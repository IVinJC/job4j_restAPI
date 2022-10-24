create table if not exists room
(
    id   serial primary key,
    name varchar(500),
    username varchar(250),
    message varchar(1000)
);

create table if not exists person
(
    id       serial primary key,
    name     varchar(500) not null,
    password varchar(250) not null,
    room_id  int          references room (id)
);

create table if not exists role
(
    id        serial primary key,
    name      varchar(500) not null,
    person_id int references person (id)
);





