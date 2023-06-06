drop table if exists Users;
drop table if exists Taco_Order;
drop table if exists Taco;
drop table if exists Ingredient;
drop table if exists taco_ingredients;

create table Users (
    id bigint not null auto_increment,
    username varchar(50) not null,
    password varchar(500) not null,
    fullname varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(50) not null,
    zip varchar(50) not null,
    phone_number varchar(50) not null,
    PRIMARY KEY (id)
);

create table if not exists Taco_Order (
    id bigint not null auto_increment,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null,
    user_id bigint not null,
    PRIMARY KEY (id)
);

create table if not exists Taco (
    id bigint not null auto_increment,
    name varchar(50) not null,
    created_at timestamp not null,
    PRIMARY KEY (id)
);


create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists taco_ingredients (
    taco_id bigint not null auto_increment,
    ingredients_id varchar(4) not null,
    PRIMARY KEY (taco_id)
);

