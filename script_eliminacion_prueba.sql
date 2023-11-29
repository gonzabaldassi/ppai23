DELETE FROM RespuestaDeCliente
WHERE llamada = 7;

-- Eliminar la llamada
DELETE FROM Llamada
WHERE numeroLlamada = 7;

-- Eliminar el cliente
DELETE FROM Cliente
WHERE dni = 43604050;