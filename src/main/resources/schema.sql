drop table if exists Taco_Order;
drop table if exists Taco;
drop table if exists Ingredient;
drop table if exists taco_ingredients;
drop table if exists users;
drop table if exists authorities;
drop table if exists ix_auth_username;

create table if not exists Taco_Order (
    id identity,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Taco (
    id identity,
    name varchar(50) not null,
    created_at timestamp not null
);


create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists taco_ingredients (
    taco_id bigint not null,
    ingredients_id varchar(4) not null
);

create table if not exists users (
    username varchar2(50) not null primary key,
    password varchar2(50) not null,
    enabled char(1) default '1'
);

create table if not exists authorities (
    username varchar2(50) not null,
    authority varchar2(50) not null,
    constraint fk_authorities_users
        foreign key(username) references users(username)
);

create unique index ix_auth_username
    on authorities (username, authority);
