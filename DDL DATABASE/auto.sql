create table if not exists auto
(
    id_auto     serial
        primary key,
    marca       varchar(50),
    modello     varchar(50),
    targa       varchar(10),
    disponibile boolean,
    porte       integer     default 0 not null,
    stato       varchar(20) default 'DISPONIBILE'::character varying
);

alter table auto
    owner to postgres;

