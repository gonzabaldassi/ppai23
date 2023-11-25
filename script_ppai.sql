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


/* Datos para la tabla Estado */
INSERT INTO Estado (nombre) VALUES
('Activo'),
('Inactivo'),
('En espera');

/* Datos para la tabla Cliente */ 
INSERT INTO Cliente (nombreCompleto, nroCelular,dni) VALUES
('Salera, Roberto', '123456789',43604050),
('Antunez, Jeremias', '987654321',43604051),
('Goti, Franco', '555666777',43604052);

/* Datos para la tabla RespuestaPosible */
INSERT INTO RespuestaPosible (descripcion, valor) VALUES
('Sí', 1),
('No', 0),
('Neutral', 2);

/* Datos para la tabla Llamada */
INSERT INTO Llamada (descripcionOperador, detalleAccionRequerida, duracion, encuestaEnviada, numeroLlamada, estado, cliente, observacionAuditor) VALUES
('Consulta de servicio', 'Ninguna', 120, true, 7, 1, 1, 'Todo en orden');

/* Datos para la tabla RespuestaDeCliente */
INSERT INTO RespuestaDeCliente (fechaEncuesta, respuestaPosible, llamada) VALUES
('11-25-2022', 1, 7),
('11-26-2022', 2, 7),
('11-27-2022', 3, 7);

/* Datos para la tabla Pregunta */
INSERT INTO Pregunta (descripcionPregunta) VALUES
('Esta satisfecho con nuestro servicio?'),
('Hay algo que podamos mejorar?');

/* Datos para la tabla Encuesta */
INSERT INTO Encuesta (descripcion, fechaInicioVigencia, fechaFinVigencia) VALUES
('Encuesta de satisfacción', '11-1-2022', '11-30-2022');

/* Datos para las tablas intermedias */
/* PreguntaARespuestaPosible */
INSERT INTO PreguntaARespuestaPosible (respuestaPosible, pregunta) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(3, 2);

/* EncuestaAPregunta */ 
INSERT INTO EncuestaAPregunta (encuesta, pregunta) VALUES
(1, 1),
(1, 2);



