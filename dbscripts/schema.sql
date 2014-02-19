create database circuitdb;
use circuitdb;
create table component (
  id integer not null auto_increment primary key, 
  type integer not null, 
  name varchar(20) not null
);
create table resistance (
  id integer not null primary key, 
  impedence integer not null
);

