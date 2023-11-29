\c postgres
drop database PPAI;
create database PPAI;
ALTER DATABASE PPAI SET datestyle TO 'ISO, MDY';
\c ppai;


DROP TABLE IF exists Llamada;
DROP TABLE IF exists PreguntaARespuestaPosible;
DROp Table IF exists EncuestaAPregunta; 
DROP Table IF exists Encuesta;
DROP TABLE IF exists Pregunta;
DROP Table IF exists RespuestaPosible;
DROP Table IF exists RespuestaDeCliente;
DROP Table IF exists Estado; 
DROP Table IF exists Cliente; 

CREATE TABLE Estado (
    id SERIAL,
    nombre varchar(50),
    CONSTRAINT PK_Estado PRIMARY KEY (id)
) ;

CREATE TABLE Cliente (
    id SERIAl, 
    nombreCompleto VARCHAR(50),
    dni INT,
    nroCelular VARCHAR(50), 
    CONSTRAINT Pk_Cliente PRIMARY KEY (id)
);

CREATE Table RespuestaPosible ( 
    id SERIAL,
    descripcion VARCHAR(50), 
    valor INT,
    CONSTRAINT Pk_RespuestaPosible PRIMARY KEY (id)
);

CREATE Table Llamada (
    numeroLlamada INT,
    descripcionOperador VARCHAR(50),
    detalleAccionRequerida VARCHAR(50),
    duracion INT,
    encuestaEnviada BOOLEAN,
    estado INT,
    cliente INT,
    observacionAuditor VARCHAR(50), 
    CONSTRAINT Pk_Llamada PRIMARY KEY (numeroLlamada),
    CONSTRAINT Fk_Estado FOREIGN KEY (estado) REFERENCES Estado (id),
    CONSTRAINT Fk_Cliente FOREIGN KEY (cliente) REFERENCES Cliente (id)
);

CREATE Table RespuestaDeCliente (
    id SERIAL, 
    ordenRespuesta INT,
    fechaEncuesta DATE, 
    respuestaPosible INT, 
    llamada INT,
    CONSTRAINT Pk_RespuestaDeCliente PRIMARY KEY (id), 
    CONSTRAINT Fk_RespuestaPosible FOREIGN KEY (respuestaPosible) REFERENCES RespuestaPosible (id),
    CONSTRAINT Fk_LLamada FOREIGN KEY (llamada) REFERENCES Llamada (numeroLlamada)
);

CREATE Table Pregunta (
    id SERIAL,
    descripcionPregunta VARCHAR(50), 
    CONSTRAINT Pk_Pregunta PRIMARY KEY (id)
);

CREATE TABLE Encuesta (
    id SERIAL, 
    descripcion VARCHAR(50),
    fechaFinVigencia DATE, 
    fechaInicioVigencia DATE, 
    CONSTRAINT Pk_Encuesta PRIMARY KEY (id)
);

/* Tablas Intermedias */

CREATE Table PreguntaARespuestaPosible (
    id SERIAL,
    respuestaPosible INT,
    pregunta INT, 
    CONSTRAINT Pk_PreguntaARespuestaPosible PRIMARY KEY (id),
    CONSTRAINT Fk_RespuestaPosible FOREIGN KEY (respuestaPosible) REFERENCES RespuestaPosible (id),
    CONSTRAINT Fk_Pregunta FOREIGN KEY (pregunta) REFERENCES Pregunta (id)
);

CREATE Table EncuestaAPregunta (
    id SERIAL,
    encuesta INT,
    pregunta INT,
    CONSTRAINT Pk_EncuestaAPregunta PRIMARY KEY (id),
    CONSTRAINT Fk_Encuesta FOREIGN KEY (encuesta) REFERENCES Encuesta (id),
    CONSTRAINT Fk_Pregunta FOREIGN KEY (pregunta) REFERENCES Pregunta (id)
);


/*
INSERT INTO Estado (nombre) VALUES
('Activo'),
('Inactivo'),
('En espera');


INSERT INTO Cliente (nombreCompleto, nroCelular,dni) VALUES
('Salera, Roberto', '123456789',43604050),
('Antunez, Jeremias', '987654321',43604051),
('Goti, Franco', '555666777',43604052);

INSERT INTO RespuestaPosible (descripcion, valor) VALUES
('Sí', 1),
('No', 0),
('Neutral', 2);


INSERT INTO Llamada (descripcionOperador, detalleAccionRequerida, duracion, encuestaEnviada, numeroLlamada, estado, cliente, observacionAuditor) VALUES
('Consulta de servicio', 'Ninguna', 120, true, 7, 1, 1, 'Todo en orden');


INSERT INTO RespuestaDeCliente (fechaEncuesta, respuestaPosible, llamada) VALUES
('11-25-2022', 1, 7),
('11-26-2022', 2, 7),
('11-27-2022', 3, 7);


INSERT INTO Pregunta (descripcionPregunta) VALUES
('Esta satisfecho con nuestro servicio?'),
('Hay algo que podamos mejorar?');


INSERT INTO Encuesta (descripcion, fechaInicioVigencia, fechaFinVigencia) VALUES
('Encuesta de satisfacción', '11-1-2022', '11-30-2022');


INSERT INTO PreguntaARespuestaPosible (respuestaPosible, pregunta) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(3, 2);

INSERT INTO EncuestaAPregunta (encuesta, pregunta) VALUES
(1, 1),
(1, 2);
*/

INSERT INTO Estado (nombre) VALUES
('Iniciada'),
('Finalizada');

/* Datos para la tabla Cliente */
INSERT INTO Cliente (nombreCompleto, dni, nroCelular) VALUES
('Roger Federer', 20357987, '3534078294'),
('Novak Djokovic', 22567987, '3534678341'),
('Rafael Nadal', 22345876, '3534079234'),
('Carlos Alcaraz', 40398523, '3538908213'),
('Maria Sharapova', 27997312, '3536098123');

/* Datos para la tabla RespuestaPosible */
INSERT INTO RespuestaPosible (descripcion, valor) VALUES
('Si', 0),
('No', 1),
('Malo', 2),
('Decente', 3),
('Bueno', 4),
('Excelente', 5);

/* Datos para la tabla Llamada */
INSERT INTO Llamada (numeroLlamada, descripcionOperador, detalleAccionRequerida, duracion, encuestaEnviada, estado, cliente) VALUES
(1, 'Prueba1', 'Prueba1', 10, true, 2, 1),
(2, 'Prueba 2', 'Prueba 2', 0, false, 1, 2),
(3, 'Prueba 3', 'Prueba 3', 9, true, 2, 2),
(4, 'Prueba 4', 'Prueba 4', 7, true, 2, 3),
(5, 'Prueba 5', 'Prueba 5', 14, true, 2, 4),
(6, 'Prueba 6', 'Prueba 6', 5, true, 2, 5);

/* Datos para la tabla RespuestaDeCliente */
INSERT INTO RespuestaDeCliente (fechaEncuesta, respuestaPosible, llamada,ordenRespuesta) VALUES
('06-17-2023',4,1,1),
('06-17-2023',2,1,2),
('06-17-2023',1,1,3),
('04-23-2023',6,3,1),
('04-23-2023',1,3,2),
('04-23-2023',2,3,3),
('07-25-2023',1,4,1),
('07-25-2023',1,4,2),
('07-25-2023',5,4,3),
('09-05-2023',2,5,1),
('09-05-2023',2,5,2),
('09-05-2023',3,5,3),
('01-02-2023',3,6,1),
('01-02-2023',1,6,2),
('01-02-2023',2,6,3);

/* Datos para la tabla Pregunta */
INSERT INTO Pregunta (descripcionPregunta) VALUES
('¿Como calificaria el servicio?'),
('¿Estas conforme con el servicio?'),
('¿Le recomendaria el servicio a un amigo?'),
('¿Como fue la calidad de la llamada?');

/* Datos para la tabla Encuesta */
INSERT INTO Encuesta (descripcion, fechaInicioVigencia, fechaFinVigencia) VALUES
('encuesta1', '01-01-2023', '06-30-2023'),
('encuesta2', '07-01-2023', '12-31-2023');

/* Datos para las tablas intermedias */
/* PreguntaARespuestaPosible */
INSERT INTO PreguntaARespuestaPosible (respuestaPosible, pregunta) VALUES
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(1, 2),
(2, 2),
(1, 3),
(2, 3),
(3, 4),
(4, 4),
(5, 4),
(6, 4);

/* EncuestaAPregunta */
INSERT INTO EncuestaAPregunta (encuesta, pregunta) VALUES
(1,1),
(1,2),
(1,3),
(2,2),
(2,3),
(2,4);