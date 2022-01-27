create
    database baza_testowa;

-- create table comment
-- (
--     id      int8 not null,
--     content varchar(255),
--     date    timestamp,
--     mem_id  int8 not null,
--     primary key (id)
-- );
--
-- create table mem
-- (
--     id        int8 not null,
--     base64    varchar(255),
--     sub_title varchar(255),
--     title     varchar(255),
--     primary key (id)
-- );


create sequence hibernate_sequence start 1 increment 1;

create table comment
(
    id            serial not null,
    content       varchar(255),
    creation_date timestamp,
    mem_id        int8   not null,
    primary key (id)
);

create table mem
(
    id            serial not null,
    base64        varchar(255),
    creation_date timestamp,
    description   varchar(255),
    title         varchar(255),
    primary key (id)
);

alter table if exists comment
    add constraint FKhu4715qdmtknlqxd0kv08pu9 foreign key (mem_id) references mem;
