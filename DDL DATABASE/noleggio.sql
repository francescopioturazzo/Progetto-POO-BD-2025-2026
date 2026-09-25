create table if not exists noleggio
(
    id_noleggio  serial
        primary key,
    id_cliente   integer
        references cliente,
    id_veicolo   integer,
    tipo_veicolo varchar(10),
    data_inizio  date,
    data_fine    date,
    costo        numeric(10, 2)
);

alter table noleggio
    owner to postgres;

