create database OcupareSali;
use OcupareSali;

create table User(
	id INT NOT NULL AUTO_INCREMENT, 
  nume VARCHAR(50) NOT NULL, 
  mail VARCHAR(50) NOT NULL, 
  parola VARCHAR(100) NOT NULL,
  telefon VARCHAR(35) NOT NULL,
  tip ENUM('admin', 'user') NOT NULL,
	CONSTRAINT user_id_pk PRIMARY KEY(id)
  
);

create table Eveniment(
	id INT NOT NULL AUTO_INCREMENT,
  nume VARCHAR(50) NOT NULL,
  tip ENUM('curs', 'laborator', 'seminar', 'conferinta', 'prezentare', 'general') NOT NULL,
  periodicitate ENUM('saptamanal', 'unic', 'la doua saptamani', 'anual', 'semestrial', 'lunar'),
  inceput DATETIME NOT NULL,
  sfarsit DATETIME NOT NULL,
  detinatorID INT NOT NULL,
  participanti VARCHAR(100),
  CONSTRAINT eveniment_id_pk PRIMARY KEY(id)
);

create table RezervareSala(
	id INT NOT NULL AUTO_INCREMENT,
  idEveniment INT NOT NULL,
  idSala INT NOT NULL, 
  CONSTRAINT rezervareSala_id_pk PRIMARY KEY(id)
);


create table Sala(
	id INT NOT NULL AUTO_INCREMENT,
  nrLocuri INT NOT NULL,
  proiector BOOLEAN,
  CONSTRAINT Sala_id_pk PRIMARY KEY(id)
);

insert into RezervareSala VALUES (default, 1, 1);