CREATE TABLE IF NOT EXISTS Kayttaja(id IDENTITY PRIMARY KEY, admin BOOLEAN DEFAULT false, nimi VARCHAR(255) , kayttajatunnus VARCHAR(255) UNIQUE, passwd VARCHAR(255));
DELETE FROM Kayttaja;
INSERT INTO Kayttaja VALUES(1, false, 'user', 'user', 'user');
INSERT INTO Kayttaja VALUES(2,true,  'admin','admin', 'admin');
