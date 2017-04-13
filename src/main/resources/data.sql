CREATE TABLE IF NOT EXISTS Kayttaja(id IDENTITY PRIMARY KEY, admin BOOLEAN DEFAULT false, nimi VARCHAR(255) , kayttajatunnus VARCHAR(255) UNIQUE, passwd VARCHAR(255));
DELETE FROM Kayttaja;
INSERT INTO Kayttaja VALUES(1, false, 'user', 'user', 'user');
INSERT INTO Kayttaja VALUES(2,true,  'admin','admin', 'admin');
CREATE TABLE IF NOT EXISTS Elokuva(id IDENTITY PRIMARY KEY, nimi VARCHAR(40), tyylilaji VARCHAR(30));
DELETE FROM Elokuva;
INSERT INTO Elokuva VALUES(1, 'inception', 'tieteiselokuva');
INSERT INTO Elokuva VALUES(2, 'lego movie', 'animaatio');
CREATE TABLE IF NOT EXISTS Varaus(id IDENTITY PRIMARY KEY, elokuvanNimi VARCHAR(40), varaajanId INT, naytoksenId INT, rivi INT, paikka INT);
DELETE FROM Varaus;
INSERT INTO Varaus VALUES(1, 'inception', 1, 1, 2, 2);
INSERT INTO Varaus VALUES(2, 'inception', 1, 1, 2, 3);

