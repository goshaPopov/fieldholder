CREATE SEQUENCE account_seq INCREMENT 1 START WITH 10;

CREATE SEQUENCE field_seq START WITH 10;

CREATE TABLE ACCOUNT
(
    id number NOT NULL,
    name varchar(201),
    email varchar(201)
);

CREATE TABLE FIELD
(
    id number NOT NULL,
    name varchar(201) NOT NULL,
    lat double NOT NULL,
    lon double NOT NULL,
    account_id number NOT NULL
);
