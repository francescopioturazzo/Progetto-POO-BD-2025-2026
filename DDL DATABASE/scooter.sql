create table if not exists scooter
(
    id_scooter  serial
        primary key,
    marca       varchar(50),
    modello     varchar(50),
    targa       varchar(10),
    disponibile boolean,
    cilindrata  integer default 125
);

alter table scooter
    owner to postgres;

