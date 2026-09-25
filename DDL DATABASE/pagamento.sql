create table if not exists pagamento
(
    id_pagamento   serial
        primary key,
    id_noleggio    integer
        references noleggio,
    metodo         varchar(20),
    importo        numeric(10, 2),
    data_pagamento date
);

alter table pagamento
    owner to postgres;

