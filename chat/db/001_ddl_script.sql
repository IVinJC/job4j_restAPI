create table person
(
    id serial primary key,
    name varchar(50) not null
);

create table role
(
    id serial primary key,
    name varchar(50) not null,
    person_id int references person(id)
);




