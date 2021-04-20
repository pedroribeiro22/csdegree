DROP TABLE IF EXISTS "User" CASCADE;
DROP TABLE IF EXISTS Game CASCADE;
DROP TABLE IF EXISTS Platform CASCADE;
CREATE TABLE "User" (ID SERIAL NOT NULL, Name varchar(255), Email varchar(255), Password varchar(255), PRIMARY KEY (ID));
CREATE TABLE Game (ID SERIAL NOT NULL, Name varchar(255), Year int4 NOT NULL, Price varchar(255), Description varchar(255), PRIMARY KEY (ID));
CREATE TABLE Platform (ID SERIAL NOT NULL, Name varchar(255), Year int4 NOT NULL, Description varchar(255), Manufacturer varchar(255), PRIMARY KEY (ID));
