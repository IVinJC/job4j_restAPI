
create table if not exists person
(
    id       serial primary key,
    name     varchar(50) not null,
    password varchar(50) not null
);

create table if not exists role
(
    id        serial primary key,
    name      varchar(50) not null,
    person_id int references person (id)
);





