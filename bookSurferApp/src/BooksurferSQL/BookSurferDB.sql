--drop table author;
CREATE TABLE autor (
    vorname      VARCHAR(32),
    nachname     VARCHAR(32),
    gebdatum     DATE,
    autorid     decimal(6) NOT NULL,
    biographie   VARCHAR(4000),
);

ALTER TABLE autor ADD CONSTRAINT autor_pk PRIMARY KEY ( autorid );

CREATE SEQUENCE seq_autor
AS BIGINT
START WITH 1;

--drop table buch;
CREATE TABLE buch (
    titel           varchar(64),
    buchid          decimal(6) NOT NULL,
    klappentext     varchar(1500),
    releasedatum    DATE,
    seitenanzahl    decimal(4),
    kapitelanzahl   decimal(2),
    preis           decimal(5,2)
);

ALTER TABLE buch ADD CONSTRAINT buch_pk PRIMARY KEY ( buchid );

CREATE SEQUENCE seq_buch
AS BIGINT
START WITH 1;

--drop table genre;
CREATE TABLE genre (
    name           varchar(32),
    beschreibung   varchar(1024),
    genreid        decimal(6) NOT NULL
);

ALTER TABLE genre ADD CONSTRAINT genre_pk PRIMARY KEY ( genreid );
--drop table kapitel;
CREATE TABLE kapitel (
    überschrift   varchar(100),
    nummer        decimal(2) NOT NULL,
    kapitelid     decimal(6) NOT NULL,
    buch_buchid   decimal(6) NOT NULL,
    textdateiurl   varchar(100)
);

CREATE SEQUENCE seq_genre
AS BIGINT
START WITH 1;

ALTER TABLE kapitel ADD CONSTRAINT kapitel_pk PRIMARY KEY ( kapitelid );
--drop table kommentar;
CREATE TABLE kommentar (
    text          varchar(1000),
    überschrift   varchar(32),
    sterne        decimal(2),
    kommentarid   decimal(6) NOT NULL,
    user_uid      decimal(6) NOT NULL
);

ALTER TABLE kommentar ADD CONSTRAINT kommentar_pk PRIMARY KEY ( kommentarid );
--drop table relation_1;
CREATE TABLE relation_1 (
    user_uid      decimal(6) NOT NULL,
    buch_buchid   decimal(6) NOT NULL,
    kaufdatum     timestamp
);

ALTER TABLE relation_1 ADD CONSTRAINT relation_1_pk PRIMARY KEY ( user_uid,
                                                                  buch_buchid );
--drop table relation_2;
CREATE TABLE relation_2 (
    buch_buchid     decimal(6) NOT NULL,
    genre_genreid   decimal(6) NOT NULL
);

ALTER TABLE relation_2 ADD CONSTRAINT relation_2_pk PRIMARY KEY ( buch_buchid,
                                                                  genre_genreid );
--drop table relation_3;
CREATE TABLE relation_3 (
    buch_buchid       decimal(6) NOT NULL,
    autor_autorid   decimal(6) NOT NULL
);

ALTER TABLE relation_3 ADD CONSTRAINT relation_3_pk PRIMARY KEY ( buch_buchid,
                                                                  autor_autorid );
--drop table relation_7;
CREATE TABLE relation_7 (
    kommentar_kommentarid   decimal(6) NOT NULL,
    buch_buchid             decimal(6) NOT NULL
);

ALTER TABLE relation_7 ADD CONSTRAINT relation_7_pk PRIMARY KEY ( kommentar_kommentarid,
                                                                  buch_buchid );
--drop table seite;
CREATE TABLE seite (
    text                varchar(8000),
    seitennr            decimal(4) NOT NULL,
    seitenid            decimal(12) NOT NULL,
    kapitel_kapitelid   decimal(6) NOT NULL
);

ALTER TABLE seite ADD CONSTRAINT seite_pk PRIMARY KEY ( seitenid );

CREATE SEQUENCE seq_user
AS BIGINT
START WITH 1;

CREATE TABLE "User" (
    "UID"          decimal(6) NOT NULL,
    benutzername   varchar(32),
    passwort       varchar(32),
    email          varchar(32),
    geld           decimal (100, 2)
);



ALTER TABLE "User" ADD CONSTRAINT user_pk PRIMARY KEY ( "UID" );
ALTER TABLE kapitel
    ADD CONSTRAINT kapitel_buch_fk FOREIGN KEY ( buch_buchid )
        REFERENCES buch ( buchid );

ALTER TABLE kommentar
    ADD CONSTRAINT kommentar_user_fk FOREIGN KEY ( user_uid )
        REFERENCES "User" ( "UID" );

ALTER TABLE relation_1
    ADD CONSTRAINT relation_1_buch_fk FOREIGN KEY ( buch_buchid )
        REFERENCES buch ( buchid );

ALTER TABLE relation_1
    ADD CONSTRAINT relation_1_user_fk FOREIGN KEY ( user_uid )
        REFERENCES "User" ( "UID" );

ALTER TABLE relation_2
    ADD CONSTRAINT relation_2_buch_fk FOREIGN KEY ( buch_buchid )
        REFERENCES buch ( buchid );

ALTER TABLE relation_2
    ADD CONSTRAINT relation_2_genre_fk FOREIGN KEY ( genre_genreid )
        REFERENCES genre ( genreid );

ALTER TABLE relation_3
    ADD CONSTRAINT relation_3_autor_fk FOREIGN KEY ( autor_autorid )
        REFERENCES autor ( autorid );

ALTER TABLE relation_3
    ADD CONSTRAINT relation_3_buch_fk FOREIGN KEY ( buch_buchid )
        REFERENCES buch ( buchid );

ALTER TABLE relation_7
    ADD CONSTRAINT relation_7_buch_fk FOREIGN KEY ( buch_buchid )
        REFERENCES buch ( buchid );

ALTER TABLE relation_7
    ADD CONSTRAINT relation_7_kommentar_fk FOREIGN KEY ( kommentar_kommentarid )
        REFERENCES kommentar ( kommentarid );

ALTER TABLE seite
    ADD CONSTRAINT seite_kapitel_fk FOREIGN KEY ( kapitel_kapitelid )
        REFERENCES kapitel ( kapitelid );
