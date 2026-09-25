create table if not exists cliente
(
    id_cliente serial
        primary key,
    nome       varchar(50),
    cognome    varchar(50),
    telefono   varchar(20),
    email      varchar(100)
);

alter table cliente
    owner to postgres;

