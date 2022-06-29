create sequence hibernate_sequence start 1 increment 1;
create table edition
(
    id          int8 generated by default as identity,
    price       int4 not null,
    text_en     varchar(2048) not null,
    text_ua     varchar(2048) not null,
    title_en    varchar(255) not null,
    title_image varchar(255),
    title_ua    varchar(255) not null,
    genre_id    int8,
    primary key (id)
);
create table genre
(
    id      int8 generated by default as identity,
    name_en varchar(255),
    name_ua varchar(255),
    primary key (id)
);
create table role
(
    user_id int8 not null,
    roles   varchar(255)
);
create table user_edition
(
    edition_id int8 not null,
    user_id    int8 not null,
    primary key (edition_id, user_id)
);
create table usr
(
    id         int8 generated by default as identity,
    balance    int4 not null,
    email      varchar(255) not null,
    first_name varchar(255),
    password   varchar(255) not null,
    user_image varchar(255),
    username   varchar(255) not null,
    primary key (id)
);
alter table if exists edition
    add constraint FK3cbc53xwlnnyacgxfgqpv3goo foreign key (genre_id) references genre;
alter table if exists role
    add constraint FKghqm2pia0ngnqyt92adfhq26d foreign key (user_id) references usr;
alter table if exists user_edition
    add constraint FK9t8qqdcgw2oc9aj06k715l4et foreign key (edition_id) references edition;
alter table if exists user_edition
    add constraint FKjmtsb74x3o5jlb0d4edeu3wsp foreign key (user_id) references usr;