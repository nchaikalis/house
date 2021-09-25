create table person
(
    person_id     serial
        constraint person_pk
            primary key,
    first_name    varchar(255)                                     not null,
    last_name     varchar(255)                                     not null,
    username      varchar(255)                                     not null,
    user_password varchar(255)                                     not null,
    user_role     varchar(255) default 'viewer'::character varying not null
);

alter table person
    owner to postgres;

create unique index person_person_id_uindex
    on person (person_id);

create unique index person_username_uindex
    on person (username);

create table asset
(
    asset_id     serial
        constraint asset_pk
            primary key,
    area         varchar(255) not null,
    price        integer      not null,
    availability varchar(255) not null,
    square_meter integer      not null,
    person_id    integer
        constraint person_id
            references person
            on update cascade on delete cascade
);

alter table asset
    owner to postgres;

create unique index asset_asset_id_uindex
    on asset (asset_id);
