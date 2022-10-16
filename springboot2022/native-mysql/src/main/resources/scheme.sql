create table product
(
    id          serial primary key,
    name        varchar(255) not null,
    description varchar(1000)
);
insert into product(id, name, description)
values (1, "test", "test");