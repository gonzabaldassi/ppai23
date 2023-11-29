INSERT INTO Cliente (nombreCompleto, dni, nroCelular) VALUES
('Roberto Salera', 43604050, '3535292819');

INSERT INTO Llamada (numeroLlamada, descripcionOperador, detalleAccionRequerida, duracion, encuestaEnviada, estado, cliente) VALUES
(7, 'Prueba 7', 'Prueba 7', 19, true, 2, 6);

INSERT INTO RespuestaDeCliente (fechaEncuesta, respuestaPosible, llamada,ordenRespuesta) VALUES
('12-05-2023',2,7,1),
('12-05-2023',2,7,2),
('12-05-2023',6,7,1);