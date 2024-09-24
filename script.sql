create sequence composants_id_seq
    as integer;

alter sequence composants_id_seq owner to bwissal;

create sequence composant_id_seq
    as integer;

alter sequence composant_id_seq owner to bwissal;

create table clients
(
    id               serial
        primary key,
    nom              varchar(255) not null,
    adresse          varchar(255),
    telephone        varchar(20),
    estprofessionnel boolean
);

alter table clients
    owner to bwissal;

create table composants
(
    id            integer default nextval('composant_id_seq'::regclass) not null
        primary key,
    nom           varchar(255)                                          not null,
    coutunitaire  numeric(10, 2),
    typecomposant varchar(50),
    tauxtva       numeric(5, 2)
);

alter table composants
    owner to bwissal;

alter sequence composant_id_seq owned by composants.id;

create table devis
(
    id            serial
        primary key,
    montantestime numeric(10, 2),
    dateemission  date,
    datevalidite  date,
    accepte       boolean
);

alter table devis
    owner to bwissal;

create table projets
(
    id                serial
        primary key,
    nomprojet         varchar(255) not null,
    margebeneficiaire double precision,
    couttotal         double precision,
    etatprojet        varchar(50),
    clientid          integer
                                   references clients
                                       on delete set null
);

alter table projets
    owner to bwissal;

create table materiaux
(
    quantite           numeric(10, 2),
    couttransport      numeric(10, 2),
    coefficientqualite numeric(5, 2),
    project_id         integer
        references projets
)
    inherits (composants);

alter table materiaux
    owner to bwissal;

create table maindoeuvres
(
    tauxhoraire   numeric(10, 2),
    heurestravail numeric(10, 2),
    productivite  numeric(5, 2),
    project_id    integer
        references projets
)
    inherits (composants);

alter table maindoeuvres
    owner to bwissal;


