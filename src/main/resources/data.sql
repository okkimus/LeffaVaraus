DELETE FROM Kayttaja;
INSERT INTO Kayttaja VALUES(1, false, 'user', 'Oikeanimi', 'sala');
INSERT INTO Kayttaja VALUES(2, true,  'admin','admin', 'admin');
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

