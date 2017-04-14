CREATE TABLE IF NOT EXISTS Kayttaja(id IDENTITY PRIMARY KEY, admin BOOLEAN DEFAULT false, nimi VARCHAR(255) , kayttajatunnus VARCHAR(255) UNIQUE, passwd VARCHAR(255));
DELETE FROM Kayttaja;
INSERT INTO Kayttaja VALUES(1, false, 'user', 'user', 'user');
INSERT INTO Kayttaja VALUES(2,true,  'admin','admin', 'admin');
CREATE TABLE IF NOT EXISTS Elokuva(id IDENTITY PRIMARY KEY, nimi VARCHAR(40), tyylilaji VARCHAR(30), kuvanOsoite VARCHAR(30));
DELETE FROM Elokuva;
INSERT INTO Elokuva VALUES(1, 'Moonlight', 'Draama','../../elokuvajulisteet/moonlight.jpg');
INSERT INTO Elokuva VALUES(2, 'Saattokeikka ', 'Komedia','../../elokuvajulisteet/saattokeikka.jpg');
INSERT INTO Elokuva VALUES(3, 'Inception', 'Tieteiselokuva','');
INSERT INTO Elokuva VALUES(4, 'Matka merelle', 'Dokumentti','../../elokuvajulisteet/matkamerelle.jpg');
INSERT INTO Elokuva VALUES(5, 'Vuosisadan naiset', 'Komedia','../../elokuvajulisteet/vuosisadannaiset.jpg');
INSERT INTO Elokuva VALUES(6, 'Lego Batman', 'Animaatio','../../elokuvajulisteet/legobatman.jpg');
CREATE TABLE IF NOT EXISTS Varaus(id IDENTITY PRIMARY KEY, elokuvanNimi VARCHAR(40), varaajanId INT, naytoksenId INT, rivi INT, paikka INT);
DELETE FROM Varaus;
INSERT INTO Varaus VALUES(1, 'Inception', 1, 1, 2, 2);
INSERT INTO Varaus VALUES(2, 'Inception', 1, 1, 2, 3);

