CREATE TABLE IF NOT EXISTS Kayttaja(id IDENTITY PRIMARY KEY, admin BOOLEAN DEFAULT false, nimi VARCHAR(255) , kayttajatunnus VARCHAR(255) UNIQUE, passwd VARCHAR(255));
DELETE FROM Kayttaja;
INSERT INTO Kayttaja VALUES(1, false, 'user', 'user', 'user');
INSERT INTO Kayttaja VALUES(2,true,  'admin','admin', 'admin');

CREATE TABLE IF NOT EXISTS Elokuva(id IDENTITY PRIMARY KEY, nimi VARCHAR(40), tyylilaji VARCHAR(30), kuvanOsoite VARCHAR(30));
DELETE FROM Elokuva;
INSERT INTO Elokuva VALUES(1,'../../elokuvajulisteet/moonlight.jpg', 'Moonlight', 'Draama');
INSERT INTO Elokuva VALUES(2,'../../elokuvajulisteet/saattokeikka.jpg', 'Saattokeikka ', 'Komedia');
INSERT INTO Elokuva VALUES(3,'', 'Inception', 'Tieteiselokuva');
INSERT INTO Elokuva VALUES(4,'../../elokuvajulisteet/matkamerelle.jpg', 'Matka merelle', 'Dokumentti');
INSERT INTO Elokuva VALUES(5,'../../elokuvajulisteet/vuosisadannaiset.jpg', 'Vuosisadan naiset', 'Komedia');
INSERT INTO Elokuva VALUES(6,'../../elokuvajulisteet/legobatman.jpg', 'Lego Batman', 'Animaatio');

CREATE TABLE IF NOT EXISTS Varaus(id IDENTITY PRIMARY KEY, elokuvanNimi VARCHAR(40), varaajanId INT, naytoksenId INT, rivi INT, paikka INT);
DELETE FROM Varaus;
INSERT INTO Varaus VALUES(1, 'Inception', 1, 1, 2, 2);
INSERT INTO Varaus VALUES(2, 'Inception', 1, 1, 2, 3);

CREATE TABLE IF NOT EXISTS Naytos(id IDENTITY PRIMARY KEY, elokuvanId VARCHAR(3) NOT NULL, sali VARCHAR(20) NOT NULL, kellonAika VARCHAR(9) NOT NULL, paiva VARCHAR(15) NOT NULL, riveja INT NOT NULL, paikkojaRivilla INT NOT NULL);
DELETE FROM Naytos;
INSERT INTO Naytos VALUES(1, '1', 'klo18', 6, 'huomenna', 5, 'isosali');
INSERT INTO Naytos VALUES(2, '1', 'klo14', 6, 'tänää', 5, 'pikkusali');
INSERT INTO Naytos VALUES(3, '3', 'klo22', 2, 'eile', 7, 'semiisosali');

