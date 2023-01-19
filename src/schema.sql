CREATE TABLE Jugador (
idJugador INT PRIMARY KEY CONSTRAINT idJugador NOT NULL,
rank TEXT,
wins TEXT,
kills TEXT,
deaths TEXT,
assists TEXT,
scoreround TEXT,
kad TEXT,
killsround TEXT,
plants TEXT,
firstbloods TEXT,
clutches TEXT,
flawless TEXT,
aces TEXT
);

CREATE TABLE Mapa (
idMapa INT PRIMARY KEY,
idJugador INT,
CONSTRAINT idjugadormapa FOREIGN KEY (idJugador) REFERENCES Jugador(idJugador)
name TEXT,
porcentaje_win TEXT,
wins TEXT,
losses TEXT,
kd TEXT,
adr TEXT,
acs TEXT,
);

CREATE TABLE Partida (
idPartida INT PRIMARY KEY,
idJugador INT,
idMapa INT,
CONSTRAINT idjugadorpartida FOREIGN KEY (idJugador) REFERENCES Jugador(idJugador),
CONSTRAINT idmapapartida FOREIGN KEY (idMapa) REFERENCES Mapa(idMapa),
name_map TEXT,
type TEXT,
result TEXT
);

CREATE TABLE Agentes (
idAgente INT PRIMARY KEY,
idJugador INT,
CONSTRAINT idjugadoragentes FOREIGN KEY (idJugador) REFERENCES Jugador(idJugador)
name TEXT,
type TEXT,
timePlayed TEXT,
matches TEXT,
win TEXT,
kd TEXT,
adr TEXT,
acs TEXT,
hs TEXT,
kast TEXT,
);

CREATE TABLE Armas (
idArma INT PRIMARY KEY,
idJugador INT,
CONSTRAINT idjugadorarmas FOREIGN KEY (idJugador) REFERENCES Jugador(idJugador)
name TEXT,
type TEXT,
kills TEXT,
deaths TEXT,
headshots TEXT,
damageRound TEXT,
killsRound TEXT,
longestKill TEXT
);