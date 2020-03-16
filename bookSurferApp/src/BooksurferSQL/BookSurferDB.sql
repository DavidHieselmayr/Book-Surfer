-- Generiert von Oracle SQL Developer Data Modeler 19.2.0.182.1216
--   am/um:        2020-03-11 06:43:05 MEZ
--   Site:      SQL Server 2012
--   Typ:      SQL Server 2012



CREATE TABLE festplatte (
    produkt_produkt_id       NUMERIC(28) NOT NULL,
    schreibgeschwindigkeit   VARCHAR(40) NOT NULL,
    lesegeschwindigkeit      VARCHAR(40) NOT NULL,
    formfaktor               NUMERIC(2, 1) NOT NULL
)

go

ALTER TABLE festplatte ADD constraint festplatte_pk PRIMARY KEY CLUSTERED (produkt_produkt_ID)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON ) go

CREATE TABLE produkt 
    ( produkt_id NUMERIC(28) NOT NULL IDENTITY NOT FOR REPLICATION , 
     preis NUMERIC (8,2) NOT NULL , 
     beschreibung VARCHAR (200) NOT NULL , 
     erstellungsdatum DATETIME NOT NULL , 
     garantie NUMERIC (2,1) NOT NULL , 
     verkaufszahl INTEGER NOT NULL , 
     pfad VARCHAR (60) NOT NULL , 
     produkt_TYPE VARCHAR (10) NOT NULL 
    )
GO 


ALTER TABLE produkt add constraint ch_inh_produkt check(produkt_type IN(
    'festplatte', 'produkt'
)) go

ALTER TABLE produkt ADD constraint produkt_pk PRIMARY KEY CLUSTERED (produkt_ID)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON ) go

ALTER TABLE festplatte
    ADD CONSTRAINT festplatte_produkt_fk FOREIGN KEY ( produkt_produkt_id )
        REFERENCES produkt ( produkt_id )
ON DELETE NO ACTION 
    ON UPDATE no action go



-- Zusammenfassungsbericht für Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             2
-- CREATE INDEX                             0
-- ALTER TABLE                              4
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE DATABASE                          0
-- CREATE DEFAULT                           0
-- CREATE INDEX ON VIEW                     0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE ROLE                              0
-- CREATE RULE                              0
-- CREATE SCHEMA                            0
-- CREATE SEQUENCE                          0
-- CREATE PARTITION FUNCTION                0
-- CREATE PARTITION SCHEME                  0
-- 
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
